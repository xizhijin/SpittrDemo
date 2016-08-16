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
	public SpittleController(SpittleRepository spittleRepository) {//������ע��
		this.spittleRepository = spittleRepository;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String spittles(Model model, /*Map map,*/
			@RequestParam(value="max", defaultValue="500") long max, 
			@RequestParam(value="count", defaultValue="20") int count) {
		model.addAttribute(spittleRepository.findSpittles(max, count));//��spittle��ӵ�ģ����,Ĭ��keyΪspittleList
		/*model.addAttribute("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));*///��ʾkey
		/*map.addAttribute("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));*///ʹ��map�滻model��ʵ��modelҲ��map
		return "spittles";//������ͼ��
	}
	
	@RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
	public String spittle(@PathVariable("spittleId") long spittleId, Model model) {
		model.addAttribute(spittleRepository.findOne(spittleId));
		return "spittle";
	}
	
}
