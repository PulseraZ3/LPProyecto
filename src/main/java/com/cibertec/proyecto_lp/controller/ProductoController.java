package com.cibertec.proyecto_lp.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.proyecto_lp.entity.Producto;
import com.cibertec.proyecto_lp.repository.CategoriaRepository;
import com.cibertec.proyecto_lp.serviceInterface.ProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // mapeo de url
    @GetMapping("/formulario") // get
    public String mostrarFormulario(Model model) {
        model.addAttribute("producto", new Producto()); // objeto vacio, el cual llenaremos
        model.addAttribute("categorias", categoriaRepository.findAll()); // objetoque busca idcategorias y muestra solo
                                                                         // el nombreCategorias
        model.addAttribute("activo", "producto");
        return "formulario-producto"; // retorna el html formulario-producto.html
    }

    @PostMapping("/guardar") // post
    public String guardarProducto(@ModelAttribute Producto producto, RedirectAttributes redirectAttributes,
            @RequestParam("file") MultipartFile file) {// recibir archivos de un formulario

        try {
            if (!file.isEmpty()) {// verficiar si no esta vacio
                String nombreImagen = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();// generar un
                                                                                                      // nombre unico
                                                                                                      // para no repetir
                String directorioImagenes = "uploads"; // nombre directorio
                Path ruta = Paths.get(directorioImagenes).toAbsolutePath().resolve(nombreImagen); // guardar imagen en
                                                                                                  // el directorio
                Files.createDirectories(ruta.getParent()); // crear directorio si no existe
                Files.copy(file.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);// guardar en el directorio
                producto.setImagen("/uploads/" + nombreImagen);// asignar ruta a la imagen
            }
            productoService.guardar(producto);
            redirectAttributes.addFlashAttribute("mensaje", "Producto guardado correctamente");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al guardar el producto: " + e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/producto/formulario";
    }

    @GetMapping("/listar") // url
    public String listarProducto(@RequestParam(defaultValue = "0") int pagina,@RequestParam(name="estado",required = false,defaultValue = "") String estado, Model model) { // cantidad de paginas
        int tamano = 10;// cantidad de productos
        Page<Producto> listaProductos = productoService.listarPaginadoPage(pagina, tamano);
        if(estado.equals("A") || estado.equals("D")){
            listaProductos = productoService.listarPorEstado(estado,pagina, tamano);
            model.addAttribute("estado", estado); // Agregar el estado al modelo para mantener el filtro
        } else {
            listaProductos = productoService.listarPaginadoPage(pagina, tamano);
            model.addAttribute("estado", ""); 

        }
        // calcula cantidad de bonotes en la paginacion
        int totalPaginas = listaProductos.getTotalPages();
        int inicio = Math.max(0, pagina - 2);
        int fin = Math.min(totalPaginas - 1, pagina + 2);

        model.addAttribute("listarProductos", listaProductos.getContent());
        model.addAttribute("totalPaginas", totalPaginas);
        model.addAttribute("paginaActual", pagina);
        model.addAttribute("inicio", inicio);
        model.addAttribute("fin", fin);
        model.addAttribute("activo", "listar");
        return "formulario-listar-producto";
    }

    @GetMapping("/desactivar/{idProducto}")
    public String desactivarProducto(@PathVariable int idProducto, RedirectAttributes redirectAttributes) {
        Producto producto = productoService.buscarId(idProducto);
        if (producto != null) {
            if ("A".equals(producto.getEstado())) { // Verificar si el estado es activo
                producto.setEstado("D"); // Cambiar el estado a desctivado
                redirectAttributes.addFlashAttribute("mensaje", "Producto desactivado correctamente");
            } else if (producto.getEstado().equals("D")) {
                producto.setEstado("A"); // Cambiar el estado a desctivado
                redirectAttributes.addFlashAttribute("mensaje", "Producto activado correctamente");
            } else {
                redirectAttributes.addFlashAttribute("mensaje", "Producto no encontrado");
                return "redirect:/producto/listar"; // Redirigir a la lista de productos
            }
            productoService.guardar(producto); // Guardar el producto actualizado
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "Producto no encontrado");
        }
        return "redirect:/producto/listar"; // Redirigir a la lista de productos
    }

    @GetMapping("/detalle/{idProducto}")
    public String detalleProducto(@PathVariable int idProducto, Model model, RedirectAttributes redirectAttributes) {
        Producto producto = productoService.buscarId(idProducto);
        if (producto != null) {
            model.addAttribute("producto", producto);
            model.addAttribute("categorias", categoriaRepository.findAll());
            return "formulario-details-producto";
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "Producto no encontrado");
            return "redirect:/producto/listar";
        }
    }

    @GetMapping("/editar/{idProducto}")
    public String editarProducto(@PathVariable int idProducto, Model model, RedirectAttributes redirectAttributes) {
        Producto producto = productoService.buscarId(idProducto);
        if (producto != null) {
            model.addAttribute("producto", producto);
            model.addAttribute("categorias", categoriaRepository.findAll());
            return "formulario-editar-producto";
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "Producto no encontrado");
            return "redirect:/producto/listar";
        }
    }

    @PostMapping("/actualizar")
    public String actualizarProducto(@ModelAttribute Producto producto, RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile file) {

        try{
            Producto productoExistente = productoService.buscarId(producto.getIdProducto()); //buscar id
            if(!file.isEmpty()){ //ver si el archivo no esta vacio
                String nombreImagen = UUID.randomUUID().toString() + "_" + file.getOriginalFilename(); //generar id unico
                Path ruta = Paths.get("uploads").toAbsolutePath().resolve(nombreImagen);//encontrar la ruta
                Files.createDirectories(ruta.getParent());//crear directorio si no hay
                Files.copy(file.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING); //copiar el archivo a la ruta creada o ya encontrada
                if(productoExistente != null && productoExistente.getImagen() !=null){ //verificar si producto existe y tiene imagen
                    Path imagenAnterior = Paths.get("uploads") // ruta de la imagen anteior
                    .resolve(Paths.get(productoExistente.getImagen()).getFileName().toString()); //obtener el nombre de la imagen anterior
                    Files.deleteIfExists(imagenAnterior); // borrar la imagen anterior si es que llega a existir
                }
                producto.setImagen("/uploads/"+nombreImagen); //asignar la nueva imagen del producto
            }else{
                if(productoExistente !=null){ //verificar si producto existe 
                    producto.setImagen(productoExistente.getImagen()); // Mantener la imagen existente si no se sube una nueva
                }
            }
            productoService.guardar(producto); // Guardar el producto actualizado
            redirectAttributes.addFlashAttribute("mensaje", "Producto actualizado correctamente");
        }
        catch (IOException e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al actualizar el producto: " + e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/producto/listar";
    }
    @GetMapping("/dashboard")
    public String mostrarDashBoard() {
        return "formulario-dashboard";
    }
}
