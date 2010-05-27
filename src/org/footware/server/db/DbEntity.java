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

import org.footware.server.db.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class DbEntity  {

	/**
	 * Persist (save or update) the object
	 */
	public void update(String row, String val) {
		String qry = "UPDATE "+ getTable() +" SET "+ row
	}

	/**
	 * Delete the object from persistence
	 */
	public void delete() {
		String qry = "DELETE FROM "+ getTable() + " WHERE id="+ getId();
	}
}
