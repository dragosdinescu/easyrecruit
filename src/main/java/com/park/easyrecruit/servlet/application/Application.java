/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.park.easyrecruit.servlet.application;

import com.park.easyrecruit.common.ApplicationDetails;
import com.park.easyrecruit.common.PositionDetails;
import com.park.easyrecruit.common.UserDetails;
import com.park.easyrecruit.ejb.ApplicationBean;
import com.park.easyrecruit.ejb.PositionBean;
import com.park.easyrecruit.ejb.UserBean;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author andrei
 */
@WebServlet(name = "Application", urlPatterns = {"/Application"})
@ServletSecurity(
        httpMethodConstraints = {
            @HttpMethodConstraint(value = "GET", rolesAllowed = {"ReadAllApplicationsRole", "ManageMyApplicationsRole"}),
            @HttpMethodConstraint(value = "POST", rolesAllowed = {"ManageMyApplicationsRole"})
        }
)
public class Application extends HttpServlet {

    @Inject
    private PositionBean positionBean;
    @Inject
    private ApplicationBean applicationBean;
    @Inject
    private UserBean userBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer positionId;
        Integer candidateId = null;

        try {
            positionId = Integer.parseInt(request.getParameter("positionId"));

            String candidateIdStr = request.getParameter("candidateId");
            if (candidateIdStr != null) {
                candidateId = Integer.parseInt(candidateIdStr);
            }
        } catch (NumberFormatException e) {
            response.setStatus(404);
            return;
        }

        ApplicationDetails application = null;
        String username = request.getUserPrincipal().getName();
        UserDetails user = userBean.findByUsername(username);

        if (candidateId != null && request.isUserInRole("ReadAllApplicationsRole")) {
            // HR GET + positionId + candidateId => read application for specified user
            application = applicationBean.get(positionId, candidateId);
            if (application == null) {
                response.setStatus(404);
                return;
            }
        } else if (candidateId == null && request.isUserInRole("ManageMyApplicationsRole")) {
            // anyone GET + positionId => read application for current user, or create if not exists
            // application for current user
            application = applicationBean.get(positionId, username);
            if (application == null) {
                PositionDetails position = positionBean.getPosition(positionId);
                if (position == null) {
                    response.setStatus(404);
                    return;
                }
                application = new ApplicationDetails();
                application.setPosition(position);
                application.setCandidate(user);
                application.setCvLink("");
            }
            request.setAttribute("edit", true);
        } else {
            response.setStatus(400);
            return;
        }

        application.getComments().sort((a, b) -> b.getDateTime().compareTo(a.getDateTime()));
        application.setEditableCommentsUserId(user.getId());
        request.setAttribute("application", application);
        request.getRequestDispatcher("/WEB-INF/pages/application.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer positionId;
        try {
            positionId = Integer.parseInt(request.getParameter("positionId"));
        } catch (NumberFormatException e) {
            response.setStatus(404);
            return;
        }

        if ("true".equals(request.getParameter("delete"))) {
            applicationBean.delete(positionId, request.getUserPrincipal().getName());
        } else {

            ApplicationDetails ad = new ApplicationDetails();
            ad.setCvLink(request.getParameter("cvLink"));

            applicationBean.save(positionId, request.getUserPrincipal().getName(), ad);
        }

        response.sendRedirect(request.getContextPath() + "/Applications");
    }
}
