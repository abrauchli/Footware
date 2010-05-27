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

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.footware.server.db.util.DB;

public abstract class DbEntity {

	protected abstract String getTable();
	protected long id;
	protected final long defaultId = -1;
	
	public abstract void update();
	
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
		try {
			return DB.insert("INSERT INTO "+ table +" ("+ c +") VALUES ("+ v +")");
		} catch (Exception e) {
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
		try {
			return DB.update("UPDATE "+ getTable() +" SET "+ c +" WHERE id="+ this.id);
		} catch (Exception e) {
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
