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

package org.footware.server.services;

import org.footware.client.services.LoginService;
import org.footware.server.db.User;
import org.footware.server.db.util.UserUtil;
import org.footware.shared.dto.UserDTO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

	@Override
	public UserDTO login(String email, String password)
			throws IllegalArgumentException {
		if (!email.matches(".+@.+\\..+"))
			throw new IllegalArgumentException("Invalid email format");
		//User u = UserUtil.getByEmail(email);
		User u = UserUtil.getIfValid(email, UserUtil.getPasswordHash(password));
		if (u != null && ! u.isDisabled()) {
			getThreadLocalRequest().getSession().setAttribute("user", u);
			return u.getUserDTO();
		}
		return null;
	}

}
