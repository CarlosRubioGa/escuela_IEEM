package mx.com.ieem.carlos.escuela.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorInicio {

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView home() 
    
    {// funcion que te dirige al index (TOP)

        ModelAndView modelAndView = null;
        modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        return modelAndView;

    }// funcion que te dirige al index (BOTTOM)
}