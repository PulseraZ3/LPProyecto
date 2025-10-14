package com.cibertec.proyecto_lp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.proyecto_lp.entity.Categoria;
import com.cibertec.proyecto_lp.entity.Producto;
import com.cibertec.proyecto_lp.serviceInterface.CategoriaService;
import com.cibertec.proyecto_lp.serviceInterface.ProductoService;

@Controller
@RequestMapping("/plazavea")
public class CatalogoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaRepository;

    @GetMapping("/") // get
    public String mostrarCatalogo(Model model) {
        Page<Producto> productos = productoService.listarPorEstado("A", 0, 10);
        List<Categoria> categorias = categoriaRepository.listarTodo();
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        return "catalogo-index"; // retorna el html formulario-producto.html
    }

    @GetMapping("/detalle/{idProducto}")
    public String detalleProducto(@PathVariable int idProducto, Model model, RedirectAttributes redirectAttributes) {
        Producto producto = productoService.buscarId(idProducto);
        if (producto != null) {
            model.addAttribute("producto", producto);
            model.addAttribute("categorias", categoriaRepository.listarTodo());
            return "detalle";
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "Producto no encontrado");
            return null;
        }
    }

    @GetMapping("/store")
    public String mostrarStore(@RequestParam(defaultValue = "0") int pagina,
            @RequestParam(name = "estado", required = false, defaultValue = "") String estado, Model model) { // cantidad
                                                                                                              // de
                                                                                                              // paginas
        int tamano = 9;// cantidad de productos
        Page<Producto> listaProductos = productoService.listarPorEstado("A", pagina, tamano);
        // calcula cantidad de bonotes en la paginacion
        int totalPaginas = listaProductos.getTotalPages();
        int inicio = Math.max(0, pagina - 2);
        int fin = Math.min(totalPaginas - 1, pagina + 2);

        model.addAttribute("productos", listaProductos.getContent());
        model.addAttribute("totalPaginas", totalPaginas);
        model.addAttribute("nombreCategoria", "Todo");

        model.addAttribute("paginaActual", pagina);
        model.addAttribute("inicio", inicio);
        model.addAttribute("fin", fin);
        model.addAttribute("activo", "listar");
        return "store_accesorios";
    }

    @GetMapping("/categoria/{idCategoria}")
    public String productoPorCategoria(@RequestParam(defaultValue = "0") int pagina, @PathVariable int idCategoria,
            Model model) {
        int tamano = 9;// cantidad de productos
        Page<Producto> listaProductos = productoService.listarPorEstadoCategoria("A", idCategoria, pagina, tamano);
        Categoria categoria = categoriaRepository.buscarId(idCategoria);
        if (categoria != null) {
            model.addAttribute("nombreCategoria", categoria.getNombreCategoria());
        } else {
            model.addAttribute("nombreCategoria", "sin categoria");
        }
        // calcula cantidad de bonotes en la paginacion
        int totalPaginas = listaProductos.getTotalPages();
        int inicio = Math.max(0, pagina - 2);
        int fin = Math.min(totalPaginas - 1, pagina + 2);

        model.addAttribute("productos", listaProductos.getContent());
        model.addAttribute("totalPaginas", totalPaginas);
        model.addAttribute("paginaActual", pagina);
        model.addAttribute("inicio", inicio);
        model.addAttribute("fin", fin);
        model.addAttribute("activo", "listar");
        return "store_accesorios";
    }

}
