package com.distributed.applications.BMI_App;

import com.distributed.applications.BMI_App.ejb.IBMICalc;
import com.distributed.applications.BMI_App.jms.JMSQueueImpl;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BMICalculator", value = "calculate-bmi")
public class BMICalculator extends HttpServlet {

    @EJB
    private IBMICalc bmiCalc;

    @EJB
    private JMSQueueImpl queue;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("name");
        float heightInCm = Float.parseFloat(request.getParameter("height"));
        float weight = Float.parseFloat(request.getParameter("weight"));

        float bmi = bmiCalc.calculate(weight, heightInCm);
        queue.sendMessage(
                "name:" + name +
                        ",height:" + heightInCm +
                        ",weight:" + weight +
                        ",bmi:" + bmi
        );

        StringBuilder responseMessage = new StringBuilder("Hello ");
        responseMessage.append(name);
        responseMessage.append(", your BMI value is ");
        responseMessage.append(bmi);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>" + responseMessage + "</h2>");
        out.println("</body></html>");
    }
}
