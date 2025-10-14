package com.cibertec.proyecto_lp.controller;
import com.cibertec.proyecto_lp.serviceInterface.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.proyecto_lp.entity.Categoria;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {


        @Autowired
    private CategoriaService categoriaService;


    @GetMapping("/formulario") 
    public String mostrarFormulario(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "formulario-categoria";
    }
    @PostMapping("/guardar")
    public String guardarCategoria(Categoria categoria, RedirectAttributes redirectAttributes) {
        categoriaService.guardar(categoria);
        redirectAttributes.addFlashAttribute("mensaje", "Categoría guardada exitosamente");
        return "redirect:/categoria/formulario"; 
    }

    @GetMapping("/listar") // url
    public String listarCategoria(@RequestParam(defaultValue = "0") int pagina,@RequestParam(name="estado",required = false,defaultValue = "") String estado, Model model) { // cantidad de paginas
        int tamano = 10;// cantidad de productos
        Page<Categoria> listaCategoria = categoriaService.listarPaginadoPage(pagina, tamano);
        if(estado.equals("A") || estado.equals("D")){
            listaCategoria = categoriaService.listarPorEstado(estado,pagina, tamano);
            model.addAttribute("estado", estado); // Agregar el estado al modelo para mantener el filtro
        } else {
            listaCategoria = categoriaService.listarPaginadoPage(pagina, tamano);
            model.addAttribute("estado", ""); // Agregar el estado al modelo para mantener el filtro

        }
        // calcula cantidad de bonotes en la paginacion
        int totalPaginas = listaCategoria.getTotalPages();
        int inicio = Math.max(0, pagina - 2);
        int fin = Math.min(totalPaginas - 1, pagina + 2);

        model.addAttribute("listarCategoria", listaCategoria.getContent());
        model.addAttribute("totalPaginas", totalPaginas);
        model.addAttribute("paginaActual", pagina);
        model.addAttribute("inicio", inicio);
        model.addAttribute("fin", fin);
        model.addAttribute("activo", "listar");
        model.addAttribute("activo", "categoria");
        return "formulario-listar-categoria";
    }
    @GetMapping("/editar/{idCategoria}")
    public String editarCategoria(@PathVariable int idCategoria, Model model, RedirectAttributes redirectAttributes) {
        Categoria categoria = categoriaService.buscarId(idCategoria);
        if (categoria != null) {
            model.addAttribute("categoria", categoria);
            return "formulario-editar-categoria";
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "Categoria no encontrado");
            return "redirect:/categoria/listar";
        }
    }
    @GetMapping("/desactivar/{idCategoria}")
    public String desactivarCategoria(@PathVariable int idCategoria, RedirectAttributes redirectAttributes) {
        Categoria categoria = categoriaService.buscarId(idCategoria);
        if (categoria != null) {
            if ("A".equals(categoria.getEstado())) { // Verificar si el estado es activo
                categoria.setEstado("D"); // Cambiar el estado a desctivado
                redirectAttributes.addFlashAttribute("mensaje", "categoria desactivado correctamente");
            } else if (categoria.getEstado().equals("D")) {
                categoria.setEstado("A"); // Cambiar el estado a desctivado
                redirectAttributes.addFlashAttribute("mensaje", "categoria activado correctamente");
            } else {
                redirectAttributes.addFlashAttribute("mensaje", "categoria no encontrado");
                return "redirect:/categoria/listar"; // Redirigir a la lista de productos
            }
            categoriaService.guardar(categoria); // Guardar el producto actualizado
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "categoria no encontrado");
        }
        return "redirect:/categoria/listar"; // Redirigir a la lista de productos
    }

    @GetMapping("/detalle/{idCategoria}")
    public String detalleCategoria(@PathVariable int idCategoria, Model model, RedirectAttributes redirectAttributes) {
        Categoria categoria = categoriaService.buscarId(idCategoria);
        if (categoria != null) {
            model.addAttribute("categoria", categoria);
            return "formulario-details-categoria";
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "categoria no encontrado");
            return "redirect:/categoria/listar";
        }
    }

}
