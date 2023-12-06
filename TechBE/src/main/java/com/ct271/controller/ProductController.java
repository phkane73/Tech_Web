package com.ct271.controller;
//Controller này dùng để xử lý về phần thêm xửa xóa sản phẩm
//Controller đáp ứng tài nguyên cho template engine thymeleaf chạy server side rendering

import com.ct271.DeleteFile;
import com.ct271.FileUploadUtil;
import com.ct271.entity.Configure;
import com.ct271.entity.Orders;
import com.ct271.entity.Product;
import com.ct271.entity.ProductImage;
import com.ct271.service.*;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

	private final IProductService iProductService;

	private final IConfigureService iConfigureService;

	private final IProductImageService iProductImageService;

	private final IOrderService iOrderService;

	private final ICartDetailService iCartDetailService;

	public ProductController(IProductService iProductService, IConfigureService iConfigureService, IProductImageService iProductImageService, IOrderService iOrderService, ICartDetailService iCartDetailService) {
		this.iProductService = iProductService;
		this.iConfigureService = iConfigureService;
		this.iProductImageService = iProductImageService;
		this.iOrderService = iOrderService;
		this.iCartDetailService = iCartDetailService;
	}

	//Xử lý hiện form thêm từng loại sản phẩm khi truy cập vào /product/category
	@GetMapping("/product/category")
	public String showAddProduct(Model model, @RequestParam("namePage") String namePage) {
		Product product = new Product();
		model.addAttribute("product", product);
		if (namePage.equals("tablet")) {
			List<String> brandList = Arrays.asList("Apple iPad", "Samsung", "Xiaomi", "OPPO");
			List<String> diskSpaceList = Arrays.asList("16GB", "32GB", "64GB", "128GB", "256GB", "512GB", "1T", "2T");
			model.addAttribute("brandList", brandList);
			model.addAttribute("diskSpaceList", diskSpaceList);
			model.addAttribute("namePage", "tablet");
			return "AdminPage/index";
		}
		if (namePage.equals("laptop")) {
			List<String> brandList = Arrays.asList("MacBook", "hp", "Asus", "Dell", "Acer", "Lenovo");
			model.addAttribute("brandList", brandList);
			model.addAttribute("namePage", "laptop");
			return "AdminPage/index";
		}
		if (namePage.equals("smartwatch")) {
			List<String> brandList = Arrays.asList("Apple", "Samsung", "Xiaomi");
			model.addAttribute("brandList", brandList);
			model.addAttribute("namePage", "smartwatch");
			return "AdminPage/index";
		}
		if (namePage.equals("earphone")) {
			List<String> brandList = Arrays.asList("Apple", "Samsung", "SONY", "OPPO");
			model.addAttribute("brandList", brandList);
			model.addAttribute("namePage", "earphone");
			return "AdminPage/index";
		}
		return "AdminPage/index";
	}

	//Xử lý khi thuc hiện thao tác submit form thêm sản phẩm
	@PostMapping("/product/category")
	public String addProduct(@RequestParam("namePage") String namePage,
							 @ModelAttribute("product") Product product, @RequestParam("photos") MultipartFile[] photos,
							 @RequestParam("imageInfor") MultipartFile imageInfor) throws IOException {
		Configure configure = iConfigureService.addConfigure(product);
		product.setConfigure(configure);
		product.setCategoryName(namePage);
		product.setIsDeleted(0);
		product.setImageProductInfor(imageInfor.getOriginalFilename());
		iProductService.addProduct(product);
		String filenameInfor = StringUtils.cleanPath(imageInfor.getOriginalFilename());
		String uploadDirInforFE = "/NienLuan/frontendProjectCt271/projectCt271Fe/public/images/" + product.getId() + "/infor/";
		String uploadDirInforBE = "./src/main/resources/static/images/" + product.getId() + "/infor/";
		FileUploadUtil.saveFile(uploadDirInforFE, filenameInfor, imageInfor);
		FileUploadUtil.saveFile(uploadDirInforBE, filenameInfor, imageInfor);
		Arrays.asList(photos).stream().forEach(photo -> {
			ProductImage productImages = new ProductImage();
			productImages.setImage(photo.getOriginalFilename());
			productImages.setProduct(product);
			iProductImageService.addImage(productImages);
			String filename = StringUtils.cleanPath(photo.getOriginalFilename());
			String uploadDir = "/NienLuan/frontendProjectCt271/projectCt271Fe/public/images/" + product.getId();
			String uploadDirBE = "./src/main/resources/static/images/" + product.getId();
			try {
				FileUploadUtil.saveFile(uploadDir, filename, photo);
				FileUploadUtil.saveFile(uploadDirBE, filename, photo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		if (namePage.equals("tablet")) {
			return "redirect:/product/category?namePage=tablet";
		}
		if (namePage.equals("laptop")) {
			return "redirect:/product/category?namePage=laptop";
		}
		if (namePage.equals("smartwatch")) {
			return "redirect:/product/category?namePage=smartwatch";
		}
		if (namePage.equals("earphone")) {
			return "redirect:/product/category?namePage=earphone";
		}
		return "AdminPage/index";
	}

	//Xử lý hiển thị danh sách sản phẩm
	@GetMapping("/listproducts")
	public String listProducts(Model model, @RequestParam("namePage") String namePage,
							   @RequestParam("p") Optional<Integer> p, HttpSession session) {
		long products = iProductService.getTotalElement();
		int numberElementOfPage = 10;
		int numberPage = (int) products / numberElementOfPage;
		if (numberPage <= 1) {
			numberPage = 1;
		}
		if (session.getAttribute("admin") != null) {
			Pageable pageable = PageRequest.of(p.orElse(0), numberElementOfPage);
			Page<Product> page = iProductService.findAll(pageable);
			int[] numberPageArr = new int[numberPage];
			for (int i = 0; i < numberPage; i++) {
				numberPageArr[i] = i;
			}
			model.addAttribute("numberPage", numberPageArr);
			model.addAttribute("allProducts", products);
			model.addAttribute("namePage", namePage);
			model.addAttribute("listProducts", page);
			model.addAttribute("currentPage", p.get());
			return "AdminPage/index";
		}
		return "redirect:/login";
	}

	@GetMapping("/listorders")
	public String listOrders(Model model, @RequestParam("namePage") String namePage,
							 @RequestParam("p") Optional<Integer> p, HttpSession session){
		long orders = iOrderService.getTotalElement();
		int numberElementOfPage = 5;
		int numberPage = (int) orders / numberElementOfPage;
		if (numberPage <= 1) {
			numberPage = 1;
		}
		if (session.getAttribute("admin") != null) {
			Pageable pageable = PageRequest.of(p.orElse(0), numberElementOfPage,
					Sort.by("date").descending());
			Page<Orders> page = iOrderService.findAll(pageable);
			int[] numberPageArr = new int[numberPage];
			for (int i = 0; i < numberPage; i++) {
				numberPageArr[i] = i;
			}
			model.addAttribute("numberPage", numberPageArr);
			model.addAttribute("allOrders", orders);
			model.addAttribute("namePage", namePage);
			model.addAttribute("listOrders", page);
			model.addAttribute("currentPage", p.get());
			return "AdminPage/index";
		}
		return "redirect:/login";
	}

	@GetMapping("listorders/orderdetail")
	public String getOrderDetail(Model model, @RequestParam("namePage") String namePage,
								 @RequestParam("id") Long id, HttpSession session){
		if (session.getAttribute("admin") != null) {
			Optional<Orders> orders = iOrderService.getOrder(id);
			model.addAttribute("namePage", namePage);
			model.addAttribute("orderdetail", orders.get().getOrderDetails());
			return "AdminPage/index";
		}
		return "redirect:/login";
	}
	//Xóa product
	@GetMapping("/listproducts/delete/{id}")
	@Transactional
	public String deleteProduct(@PathVariable("id") Long id, HttpSession session) throws IOException {
		if (session.getAttribute("admin") != null) {
			iProductService.deleteProduct(id);
			iCartDetailService.deleteAllByProductId(id);
			return "redirect:/listproducts?namePage=products&p=0";
		}
		return "redirect:/login";
	}

	@GetMapping("/listorders/check/{id}")
	public String checkOrder(@PathVariable("id") Long id, HttpSession session) throws IOException {
		if (session.getAttribute("admin") != null) {
			Optional<Orders> orders = iOrderService.getOrder(id);
			orders.get().setStatus(1);
			iOrderService.save(orders.get());
			return "redirect:/listorders?namePage=listorders&p=0";
		}
		return "redirect:/login";
	}

	//Show form update theo loai san pham
	@GetMapping("/listproducts/update")
	public String showUpdateProduct(@RequestParam("id") Long id, HttpSession session, Model model,
									@RequestParam("namePage") String namePage) {
		Optional<Product> product = iProductService.getProduct(id);
		model.addAttribute("product", product.get());
		if (namePage.equals("tablet")) {
			List<String> brandList = Arrays.asList("Apple iPad", "Samsung", "Xiaomi", "OPPO");
			List<String> diskSpaceList = Arrays.asList("16GB", "32GB", "64GB", "128GB", "256GB", "512GB", "1T", "2T");
			model.addAttribute("brandList", brandList);
			model.addAttribute("diskSpaceList", diskSpaceList);
			model.addAttribute("namePage", "tabletupdate");
			return "AdminPage/index";
		}
		if (namePage.equals("laptop")) {
			List<String> brandList = Arrays.asList("MacBook", "hp", "Asus", "Dell", "Acer", "Lenovo");
			model.addAttribute("brandList", brandList);
			model.addAttribute("namePage", "laptopupdate");
			return "AdminPage/index";
		}
		if (namePage.equals("smartwatch")) {
			List<String> brandList = Arrays.asList("Apple", "Samsung", "Xiaomi");
			model.addAttribute("brandList", brandList);
			model.addAttribute("namePage", "smartwatchupdate");
			return "AdminPage/index";
		}
		if (namePage.equals("earphone")) {
			List<String> brandList = Arrays.asList("Apple", "Samsung", "SONY", "OPPO");
			model.addAttribute("brandList", brandList);
			model.addAttribute("namePage", "earphoneupdate");
			return "AdminPage/index";
		}
		return "AdminPage/index";
	}

	//Xử lí chỉnh sửa sản phẩm
	@PostMapping("/listproducts/update")
	public String updateProduct(Model model, @ModelAttribute("product") Product product, @RequestParam("photos") MultipartFile[] photos,
								@RequestParam("imageInfor") MultipartFile imageInfor, @RequestParam("id") Long id) throws IOException {
		Configure configure = iConfigureService.updateConfigure(id, product);
		product.setConfigure(configure);
		if (!imageInfor.isEmpty()) {
			String pathImageInforProductBE = "./src/main/resources/static/images/" + id + "/infor/";
			DeleteFile.deleteFile(pathImageInforProductBE);
			String pathImageInforProductFE = "/NienLuan/frontendProjectCt271/projectCt271Fe/public/images/" + id + "/infor/";
			DeleteFile.deleteFile(pathImageInforProductFE);
			product.setImageProductInfor(imageInfor.getOriginalFilename());
			String filenameInfor = StringUtils.cleanPath(imageInfor.getOriginalFilename());
			String uploadDirInforBE = "./src/main/resources/static/images/" + id + "/infor/";
			FileUploadUtil.saveFile(uploadDirInforBE, filenameInfor, imageInfor);
			String uploadDirInforFE = "/NienLuan/frontendProjectCt271/projectCt271Fe/public/images/" + id + "/infor/";
			FileUploadUtil.saveFile(uploadDirInforFE, filenameInfor, imageInfor);
		}
		iProductService.updateProduct(id, product);
        iCartDetailService.updateCartDetail(id);
		if (photos.length > 1) {
			iProductImageService.deleteImage(product);
			String pathImageProductFE = "/NienLuan/frontendProjectCt271/projectCt271Fe/public/images/" + id + "/";
			DeleteFile.deleteFile(pathImageProductFE);
			String pathImageProductBE = "./src/main/resources/static/images/" + id + "/";
			DeleteFile.deleteFile(pathImageProductBE);
			Arrays.asList(photos).stream().forEach(photo -> {
				ProductImage productImages = new ProductImage();
				productImages.setImage(photo.getOriginalFilename());
				productImages.setProduct(product);
				iProductImageService.addImage(productImages);
				String filename = StringUtils.cleanPath(photo.getOriginalFilename());
				String uploadDirFE = "/NienLuan/frontendProjectCt271/projectCt271Fe/public/images/" + id;
				String uploadDirBE = "./src/main/resources/static/images/" + id;
				try {
					FileUploadUtil.saveFile(uploadDirFE, filename, photo);
					FileUploadUtil.saveFile(uploadDirBE, filename, photo);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
		model.addAttribute("namePage", "products");
		return "redirect:/listproducts?namePage=products&p=0";
	}
}
