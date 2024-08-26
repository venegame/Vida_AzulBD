package com.vida.azul.Controller;

import com.vida.azul.Domain.TransporteCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import com.vida.azul.Service.TransporteServiceCrud;
import com.vida.azul.Service.UsuarioServiceCrud;

@Controller
@RequestMapping("/crud_transporte")
public class TransporteControllerCrud {

    @Autowired
    private TransporteServiceCrud transporteService;

    @Autowired
    private UsuarioServiceCrud usuarioService;

    @GetMapping("/listado")
    public String obtenerTransportes(Model model) {
        List<TransporteCrud> transportes = transporteService.obtenerTransportes();
        model.addAttribute("transportes", transportes);
        return "crud_transporte/listado";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("usuarios", usuarioService.obtenerUsuarios());
        model.addAttribute("transporte", new TransporteCrud());
        return "crud_transporte/nuevo";
    }

    @PostMapping("/nuevo")
    public String agregarTransporte(TransporteCrud transporte) {
        transporteService.agregarTransporte(transporte);
        return "redirect:/crud_transporte/listado";
    }

    @GetMapping("/edita/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long idTransporte, Model model) {
        TransporteCrud transporte = transporteService.buscarPorId(idTransporte);
        model.addAttribute("usuarios", usuarioService.obtenerUsuarios());
        model.addAttribute("transporte", transporte);
        return "crud_transporte/edita";
    }

    @PostMapping("/edita/{id}")
    public String actualizarTransporte(@PathVariable("id") Long idTransporte, TransporteCrud transporte) {
        transporte.setIdTransporte(idTransporte);
        transporteService.actualizarTransporte(transporte);
        return "redirect:/crud_transporte/listado";
    }

    @GetMapping("/elimina/{id}")
    public String eliminarTransporte(@PathVariable("id") Long idTransporte) {
        transporteService.eliminarTransporte(idTransporte);
        return "redirect:/crud_transporte/listado";
    }
}
