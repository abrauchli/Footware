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

package org.footware.client.services;

import java.util.List;

import org.footware.shared.dto.ConfigDTO;
import org.footware.shared.dto.TrackDTO;
import org.footware.shared.dto.TrackVisualizationDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("track")
public interface TrackService extends RemoteService {
    List<TrackDTO> getTracks(ConfigDTO config) throws IllegalArgumentException;

    TrackVisualizationDTO getTrackVisualization(ConfigDTO config) throws IllegalArgumentException;
    
    Boolean saveChanges(TrackDTO track);
    
    Boolean deactivateTrack(TrackDTO track);
}
