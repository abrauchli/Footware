/*
 * Copyright 2010 Andreas Brauchli, René Buffat, Florian Widmer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.footware.server.db;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import org.footware.server.db.util.DB;

public abstract class DbEntity {

	protected abstract String getTable();
	protected long id;
	protected final long defaultId = -1;
	
	/**
	 * Take the camelCaseName of the field and make it camel_case_name
	 * @param s input string
	 * @return lowercase db-conform string
	 */
	public String makeDbName(String s) {
		char[] cs = s.toCharArray();
		StringBuilder sb = new StringBuilder(cs.length + 5);
		for (int i=0; i<cs.length; ++i) {
			if (Character.isUpperCase(cs[i])) {
				sb.append('_');
				sb.append(Character.toLowerCase(cs[i]));
			} else {
				sb.append(cs[i]);
			}
		}
		return sb.toString();
	}
	
	/**
	 * Store an object with all it's non null fields into the DB
	 * The field names will be used UnCamelCased -> un_camel_cased as DB cols.
	 * DO NOT USE TO UPDATE AN OBJECT!
	 */
	public void store() {
		if (getId() != defaultId)
			return;
		
		Map<String, String> kv = new HashMap<String, String>();
		for (Field f : getClass().getDeclaredFields()) {
			if ((f.getModifiers() & Modifier.STATIC) > 0)
				continue;
			if (!f.isAccessible())
				f.setAccessible(true); //it's private.. so make the field accessible :D

			Type t = f.getGenericType();
			try {
				boolean append_id = false;
				String val = null;
				
				//Int
				if (t == Integer.class) {
					val = Integer.toString(f.getInt(this));
					
				//String
				} else if (t == String.class) {
					val = (String)f.get(this);
					
				//Long
				} else if (t == Long.class) {
					val = Long.toString(f.getLong(this));

				//DbEntity
				} else if (DbEntity.class.isInstance(f.get(this))) {
					val = Long.toString(((DbEntity)f.get(this)).getId());
					append_id = true;

				//char[]
				} else if (t == char[].class) {
					val = String.valueOf((char[])f.get(this));

				//boolean
				} else if (t == boolean.class) {
					val = f.getBoolean(this) ? "1" : "0";

				//Date
				} else if (t == Date.class) {
					Date d = (Date)f.get(this);
					if (d != null)
						val = DB.sqlFormatDate(d);
				}
				if (val != null) {
					String db_field = null;
					Column c = (Column)f.getAnnotation(Column.class);
					if (c != null) {
						db_field = c.name();
					} else {
						db_field = makeDbName(f.getName());
						if (append_id)
							db_field += "_id";
					}
					kv.put(db_field, val);
				}
			} catch(IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		String[] cols = new String[kv.size()];
		String[] vals = new String[cols.length]; 
		kv.keySet().toArray(cols);
		kv.values().toArray(vals);
		insertValues(getTable(), cols, vals);
	}
	
	/**
	 * Default constructor
	 */
	public DbEntity() {
		this.id = defaultId;
	}
	
	/**
	 * Gets the id of the row
	 * @return the id of the row or defaultId if not yet committed
	 */
	public long getId() {
		return getLongValue("id", -1);
	}
	
	/**
	 * Checks for equality with another instance
	 * @param other other instance to check for equality
	 * @return true if equal, false otherwise
	 */
	public boolean equals(DbEntity other) {
		return (this.id == other.getId() /*&& this.getClass().isInstance(other)*/);
	}
	
	/**
	 * Gets whether this object is persisted
	 * @return true if persisted
	 */
	public boolean exists() {
		 return (getId() != defaultId);
	}
	
	/**
	 * Delete an object from persistence
	 */
	public void delete() {
		if (exists()) {
			try {
				DB.execute("DELETE FROM "+ getTable() +" WHERE id="+ this.id);
			} catch (Exception e) {}
		}
	}
	
	/**
	 * Gets a value of type long from a column
	 * @param column column to fetch from
	 * @param errVal error value to set in case of exceptions
	 * @return actual value if no errors happened, errVal otherwise
	 */
	public long getLongValue(String column, long errVal) {
		try {
			return DB.queryInt("SELECT "+ column +" FROM "+ getTable() +" WHERE id="+ this.id);
		} catch (Exception e) {
			return errVal;
		}
	}
	
	/**
	 * Sets a long value
	 * @param column column to set the value on
	 * @param val value to set
	 * @return number of affected rows
	 */
	public int setLongValue(String column, long val) {
		try {
			return DB.update("UPDATE "+ getTable() +" SET "+ column +"="+ val +" WHERE id="+ this.id);
		} catch (Exception e) {
			return 0;
		}
	}
	
	public int getIntValue(String column, int errVal) {
		try {
			return DB.queryInt("SELECT "+ column +" FROM "+ getTable() +" WHERE id="+ this.id);
		} catch (Exception e) {
			return errVal;
		}
	}
	
	public int setIntValue(String column, int val) {
		try {
			return DB.update("UPDATE "+ getTable() +" SET "+ column +"="+ val +" WHERE id="+ this.id);
		} catch (Exception e) {
			return 0;
		}
	}
	
	public String getStrValue(String column, String errStr) {
		try {
			return DB.queryString("SELECT "+ column +" FROM "+ getTable() +" WHERE id="+ this.id);
		} catch (Exception e) {
			return errStr;
		}
	}
	
	public int setStrValue(String column, String val) {
		try {
			return DB.update("UPDATE "+ getTable() +" SET "+ column +"='"+ val +"' WHERE id="+ this.id);
		} catch (Exception e) {
			return 0;
		}
	}
	
	public double getDblValue(String column, double errVal) {
		try {
			return DB.queryDbl("SELECT "+ column +" FROM "+ getTable() +" WHERE id="+ this.id);
		} catch (Exception e) {
			return errVal;
		}
	}
	
	public int setDblValue(String column, double val) {
		try {
			return DB.update("UPDATE "+ getTable() +" SET "+ column +"='"+ val +"' WHERE id="+ this.id);
		} catch (Exception e) {
			return 0;
		}
	}

	
	public Timestamp getTimestampValue(String column, Timestamp errVal) {
		try {
			return DB.queryTimestamp("SELECT "+ column +" FROM "+ getTable() +" WHERE id="+ this.id);
		} catch (Exception e) {
			return errVal;
		}
	}
	
	public int setTimestampValue(String column, Timestamp val) {
		try {
			return DB.update("UPDATE "+ getTable() +" SET "+ column +"='"+ val.toString() +"' WHERE id="+ this.id);
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static int insertValues(String table, String[] cols, String[] vals) {
		assert(cols.length == vals.length);
		if (cols.length == 0)
			return 0;
		
		String c = "";
		String v = "";
		for (int i=0; i<cols.length; ++i) {
			c += (i == 0 ? cols[i] : ","+ cols[i]);
			String valStr = (vals[i] == null ? "NULL" : "'"+ vals[i] +"'");
			v += (i == 0 ? valStr : ","+ valStr);
		}
		String qry = "INSERT INTO "+ table +" ("+ c +") VALUES ("+ v +")";
		try {
			return DB.insert(qry);
		} catch (Exception e) {
			System.err.println("Error while inserting: "+ qry);
			e.printStackTrace();
			return -1;
		}
	}

	public int updateValues(String[] cols, String[] vals) {
		assert(cols.length == vals.length);
		String c = "";
		for (int i=0; i<cols.length; ++i) {
			if (i>0)
				c += ","+ cols[i] + "='"+ vals[i] +"'";
			else
				c += cols[i] + "='"+ vals[i] +"'";
		}
		String qry = "UPDATE "+ getTable() +" SET "+ c +" WHERE id="+ this.id;
		try {
			return DB.update(qry);
		} catch (Exception e) {
			System.err.println("Error while updating: "+ qry);
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * Gets IDs of other table referencing this object
	 * @param table 
	 * @param id_column
	 * @return list of ids
	 */
	public List<Long> getForeignKeys(String table, String id_column) {
		try {
			List<Long> res = new LinkedList<Long>();
			ResultSet rs = DB.query("SELECT id FROM "+ table +" WHERE "+ id_column +"="+ getId());
			while (rs.next()) {
				res.add(rs.getLong(1));
			}
			return res;
		} catch (Exception e) {
			return null;
		}
	}
}
