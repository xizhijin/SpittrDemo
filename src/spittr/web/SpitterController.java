package spittr.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spittr.Spitter;
import spittr.data.SpitterRepository;

@RequestMapping(value = "/spitter")
@Controller
public class SpitterController {

	private SpitterRepository spitterRepository;

	@Autowired
	public SpitterController(SpitterRepository spitterRepository) {// 构造器注入
		this.spitterRepository = spitterRepository;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistrationForm() {
		return "registerForm";
	}

	@RequestMapping(value= "/register", method = RequestMethod.POST)
	public String processRegistration(@Valid Spitter spitter, Errors errors){
		if (errors.hasErrors()) {//如果出现校验错误，则重新返回表单
			return "registerForm";
		}
		spitterRepository.save(spitter);
		return "redirect:/spitter/" + spitter.getUsername();//重定向到基本信息页
	}
	
	@RequestMapping(value="/{username}", method = RequestMethod.GET)
	public String showSpitterProfile(@PathVariable String username, Model model) {
		Spitter spitter = spitterRepository.findByUsername(username);
		model.addAttribute(spitter);
		return "profile";
	}
}
