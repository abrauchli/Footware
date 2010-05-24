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

package org.footware.client.framework.views;


import org.footware.client.framework.pages.AbstractPage;

import com.google.gwt.user.client.ui.Grid;

/**
 * the dataview class represents the part of the app where data is displayed
 * @author flwidmer
 *
 */
public class DataView extends Grid{
	
	public DataView() {
		super(1,1);
	}
	public void displayPage(AbstractPage page){
		clearCell(0, 0);
		setWidget(0, 0, page);
	}
}
