package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String action = (String) request.getParameter("action");
        
        if (action != null && action.equals("logout"))
        {
            // invalidate the session
            session.invalidate();
            session = request.getSession();
            response.sendRedirect("ShoppingList");
            return;
        }
        
        if (username == null || username.equals(""))
        {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }
        else
        {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username_s = (String) session.getAttribute("username");
        String action = (String) request.getParameter("action");
        
        if (username_s == null || username_s.equals(""))
        {
            if(action.equals("register"))
            {
                // get useranme from request and set it to session
                String username_r = (String) request.getParameter("username");
            
                // username field is empty
                if (username_r == null || username_r.equals(""))
                {
                    getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                    return;
                }
                else 
                {
                    // register username and initailize arraylist
                    ArrayList<String> itemList = new ArrayList<String>();
                    session.setAttribute("itemList", itemList);
                    session.setAttribute("username", username_r);
                    getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                    return;
                }
            } 
        }
        else
        {
            if (action.equals("add"))
            {
                String item = (String) request.getParameter("item");
                ArrayList<String> items = (ArrayList<String>) session.getAttribute("itemList");
                if (item != null && !item.equals(""))
                {
                    items.add(item);
                    session.setAttribute("itemList", items);
                }

                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                return;        
            }
        }
    }
}