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

package org.footware.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public class TrackVisualizationPointDTO implements IsSerializable {
    
    private double xValue;
    private double yValue;
    
    public TrackVisualizationPointDTO() {
    }
    
    public TrackVisualizationPointDTO(double xValue, double yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }

    public double getX() {
        return xValue;
    }

    public void setX(double xValue) {
        this.xValue = xValue;
    }

    public double getY() {
        return yValue;
    }

    public void setY(double yValue) {
        this.yValue = yValue;
    }
    
    

}
