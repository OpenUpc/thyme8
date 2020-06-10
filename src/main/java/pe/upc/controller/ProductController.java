package pe.upc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.upc.util.Utilitario;

@Controller
public class ProductController {
	
	@Value("${app.ruta.imagenes}")
	private String ruta;

	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@PostMapping("/save")
	public String cargar(Model model, RedirectAttributes attributes, @RequestParam("archivoImagen") MultipartFile multiPart) {
		String nombreImagen = null;
		if (!multiPart.isEmpty()) {
			nombreImagen = Utilitario.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null){ 
				// Procesamos la variable nombreImagen
				System.out.println("Imagen subida");
				System.out.println("Nombre Final:" + nombreImagen);
			}
		}
		model.addAttribute("rutaWeb","/images/"+ nombreImagen);
		System.out.println("/images/"+ nombreImagen);
		attributes.addFlashAttribute("msg", "Registro Guardado");		
		System.out.println("Carga Terminada");	
		return "home"; 
	}
	
	@GetMapping("/listado")
	public String ir() {
		return "listado";
	}
	
}
