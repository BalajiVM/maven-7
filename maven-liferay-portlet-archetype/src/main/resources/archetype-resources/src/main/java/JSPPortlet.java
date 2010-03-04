/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package $groupId;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A simple portlet.
 *
 */
public class JSPPortlet extends GenericPortlet {
    private static final Log LOGGER = LogFactory.getLog(JSPPortlet.class);

    private String editJSP;
    private String helpJSP;
    private String viewJSP;


    @Override
    public void init() throws PortletException {
		editJSP = getInitParameter("edit-jsp");
		helpJSP = getInitParameter("help-jsp");
		viewJSP = getInitParameter("view-jsp");
	}

	@Override
    public void doDispatch(RenderRequest req, RenderResponse res) throws IOException, PortletException {

		String jspPage = req.getParameter("jspPage");

		if (jspPage != null) {
			include(jspPage, req, res);
		}
		else {
			super.doDispatch(req, res);
		}
	}

	@Override
    public void doEdit(RenderRequest req, RenderResponse res) throws IOException, PortletException {

		if (req.getPreferences() == null) {
			super.doEdit(req, res);
		}
		else {
			include(editJSP, req, res);
		}
	}

	@Override
    public void doHelp(RenderRequest req, RenderResponse res) throws IOException, PortletException {

		include(helpJSP, req, res);
	}

	@Override
    public void doView(RenderRequest req, RenderResponse res) throws IOException, PortletException {

		include(viewJSP, req, res);
	}

	@Override
    public void processAction(ActionRequest req, ActionResponse res) throws IOException, PortletException {
	}

	protected void include(String path, RenderRequest req, RenderResponse res) throws IOException, PortletException {

		PortletRequestDispatcher prd =
			getPortletContext().getRequestDispatcher(path);

		if (prd == null) {
			LOGGER.error(String.format("%s is not a valid include", path));
		}
		else {
			prd.include(req, res);
		}
	}

}