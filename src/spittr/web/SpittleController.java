package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spittr.data.SpittleRepository;

@RequestMapping(value="/spittles")
@Controller
public class SpittleController {
	private SpittleRepository spittleRepository;
	
	@Autowired
	public SpittleController(SpittleRepository spittleRepository) {//构造器注入
		this.spittleRepository = spittleRepository;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String spittles(Model model, /*Map map,*/
			@RequestParam(value="max", defaultValue="500") long max, 
			@RequestParam(value="count", defaultValue="20") int count) {
		model.addAttribute(spittleRepository.findSpittles(max, count));//将spittle添加到模型中,默认key为spittleList
		/*model.addAttribute("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));*///显示key
		/*map.addAttribute("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));*///使用map替换model，实际model也是map
		return "spittles";//返回视图名
	}
	
	@RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
	public String spittle(@PathVariable("spittleId") long spittleId, Model model) {
		model.addAttribute(spittleRepository.findOne(spittleId));
		return "spittle";
	}
	
}
