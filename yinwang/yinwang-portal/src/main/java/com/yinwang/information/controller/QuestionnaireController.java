package com.yinwang.information.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinwang.common.controller.BaseController;
import com.yinwang.common.utils.R;
import com.yinwang.common.utils.ShiroUtils;
import com.yinwang.information.domain.OptionDO;
import com.yinwang.information.domain.QueAnswerDO;
import com.yinwang.information.domain.QuestionnaireDO;
import com.yinwang.information.domain.TopicDO;
import com.yinwang.information.service.OptionService;
import com.yinwang.information.service.QueAnswerService;
import com.yinwang.information.service.QuestionnaireService;
import com.yinwang.information.service.TopicService;

/**
 * 问卷表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-04-14 10:11:20
 */
 
@Controller
@RequestMapping("/information/questionnaire")
public class QuestionnaireController extends BaseController {
	@Autowired
	private QuestionnaireService questionnaireService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private OptionService optionService;
	@Autowired
	private QueAnswerService queAnswerService;
	
	
	@GetMapping()
	String Questionnaire(Model model){
		boolean falg = false;
		//问卷
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("user_id", this.getUser().getId());
		List<QuestionnaireDO> questionnaireList = questionnaireService.list(param);
		if(questionnaireList.size() > 0){
			for (int i = 0; i < questionnaireList.size(); i++) {
				QuestionnaireDO qu = questionnaireList.get(i);
				Map<String, Object> paramTop = new HashMap<String, Object>();
				paramTop.put("questionnaire_id", qu.getId());
				//题目
				List<TopicDO> topicList = topicService.list(paramTop);
				//1:第一次进入；2：最后一题  
				if(topicList.size() > 0){
					TopicDO topic = topicList.get(0);
					model.addAttribute("topic", topic);
					Map<String, Object> paramOption = new HashMap<String, Object>();
					paramOption.put("topic_id", topic.getId());
					//选项
					List<OptionDO> optionList = optionService.list(paramOption);
					System.out.println("查询题目选项"+optionList.size());
					
					Map<String, Object> paramQueAnswer = new HashMap<String, Object>();
					paramQueAnswer.put("user_id", this.getUserId());
					paramQueAnswer.put("topic_id", topic.getId());
					
					//0：单选；1：多选;2:填写
					if(topic.getIsRadio() == 2){
						QueAnswerDO queAnswer = queAnswerService.getQueAnswer(paramQueAnswer);
						if(queAnswer == null)
							queAnswer = new QueAnswerDO();
						model.addAttribute("queAnswer", queAnswer);//题目总数
					}else{
						List<QueAnswerDO> listQueAnswer = queAnswerService.getQueAnswerList(paramQueAnswer);
						model.addAttribute("listQueAnswer", listQueAnswer);//题目总数
					}
					
					
					//model.addAttribute("optionList", optionList);
					model.addAttribute("numTo", topicList.size());//题目总数
					model.addAttribute("num", 1);//题目总数
					falg = true;
					break;
				}else{
					continue;
				}
			}
		}
		model.addAttribute("sta", 1);
		if(falg){
			return "information/questionnaire/diaochawenjuan";
		}else{
			return "information/questionnaire/diaochawenjuanNo";
		}
	}
	
	/**
	 * 上一题
	 * @param quid 问卷ID
	 * @param num 第几题
	 * @param model
	 * @return
	 */
	@GetMapping("/QuestionnaireShang")
	String QuestionnaireShang(Integer quid,int num,Model model){
		//问卷
		QuestionnaireDO qu = questionnaireService.get(quid);
		if(qu != null){
			Map<String, Object> paramTop = new HashMap<String, Object>();
			paramTop.put("questionnaire_id", qu.getId());
			//题目
			List<TopicDO> topicList = topicService.list(paramTop);
			//1:第一次进入；2：最后一题  
			if(topicList.size() >= num){
				TopicDO topic = topicList.get(num-1);
				model.addAttribute("topic", topic);
				Map<String, Object> paramOption = new HashMap<String, Object>();
				paramOption.put("topic_id", topic.getId());
				//选项
				List<OptionDO> optionList = optionService.list(paramOption);
				System.out.println("查询题目选项"+optionList.size());
				model.addAttribute("optionList", optionList);
				
				Map<String, Object> paramQueAnswer = new HashMap<String, Object>();
				paramQueAnswer.put("user_id", this.getUserId());
				paramQueAnswer.put("topic_id", topic.getId());
				
				//0：单选；1：多选;2:填写
				if(topic.getIsRadio() == 2){
					QueAnswerDO queAnswer = queAnswerService.getQueAnswer(paramQueAnswer);
					model.addAttribute("queAnswer", queAnswer);//题目总数
				}else{
					List<QueAnswerDO> listQueAnswer = queAnswerService.getQueAnswerList(paramQueAnswer);
					model.addAttribute("listQueAnswer", listQueAnswer);//题目总数
				}
				
				
			}
			model.addAttribute("numTo", topicList.size());//题目总数
			model.addAttribute("num", num);//题目总数
			
		}
		return "information/questionnaire/diaochawenjuan";
	}
	
	/**
	 * 下一题 
	 * @param quid 问卷ID
	 * @param tcpId 题目ID
	 * @param con 答案
	 * @param num 第几题
	 * @param model
	 * @return
	 */
	@GetMapping("/QuestionnaireXia")
	String QuestionnaireXia(Integer quid,Integer tcpId,String con, int num,Model model){
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(tcpId, con);
		System.out.println("=================="+con);
		boolean falg = questionnaireService.saveWen(this.getUser(),map);
		//问卷
		QuestionnaireDO qu = questionnaireService.get(quid);
		if(qu != null){
			Map<String, Object> paramTop = new HashMap<String, Object>();
			paramTop.put("questionnaire_id", qu.getId());
			//题目
			List<TopicDO> topicList = topicService.list(paramTop);
			//1:第一次进入；2：最后一题  
			if(topicList.size() >= num){
				TopicDO topic = topicList.get(num-1);
				model.addAttribute("topic", topic);
				Map<String, Object> paramOption = new HashMap<String, Object>();
				paramOption.put("topic_id", topic.getId());
				//选项
				List<OptionDO> optionList = optionService.list(paramOption);
				System.out.println("查询题目选项"+optionList.size());
				model.addAttribute("optionList", optionList);
				Map<String, Object> paramQueAnswer = new HashMap<String, Object>();
				paramQueAnswer.put("user_id", this.getUserId());
				paramQueAnswer.put("topic_id", topic.getId());
				
				//0：单选；1：多选;2:填写
				if(topic.getIsRadio() == 2){
					QueAnswerDO queAnswer = queAnswerService.getQueAnswer(paramQueAnswer);
					if(queAnswer == null)
						queAnswer = new QueAnswerDO();
					model.addAttribute("queAnswer", queAnswer);//题目总数
				}else{
					List<QueAnswerDO> listQueAnswer = queAnswerService.getQueAnswerList(paramQueAnswer);
					model.addAttribute("listQueAnswer", listQueAnswer);//题目总数
				}
			}
			model.addAttribute("numTo", topicList.size());//题目总数
			model.addAttribute("num", num);//题目总数
		}
		return "information/questionnaire/diaochawenjuan";
	}
	
	
	
	/**
	 * 保存提交
	 * @param quid 问卷ID
	 * @param tcpId 问题ID
	 * @param con 答案
	 * @param model
	 * @return
	 */
	@PostMapping("/QuestionnaireSum")
	@ResponseBody
	public R QuestionnaireSum(Integer quid,Integer tcpId,String con,Model model){
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(tcpId, con);
		System.out.println("zhicon:"+con);
		boolean falg = questionnaireService.saveWen1(this.getUser(),map);
		System.out.println("提交保存falg"+falg);
		if(falg){
			return R.ok();
		}
		return R.error("提交失败！");
	}
	
	/**
	 * 查询是否有 问卷调查
	 * @return
	 */
	@PostMapping("/isQuestionnaire")
	@ResponseBody
	public R isQuestionnaire(){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("user_id", this.getUser().getId());
		List<QuestionnaireDO> questionnaireList = questionnaireService.list(param);
		if(questionnaireList != null && questionnaireList.size() > 0){
			return R.ok();
		}
		return R.error("没有调查报告");
	}
	
	
	@GetMapping("/myQuestionnaire")
	String myQuestionnaire(Model model){
		//问卷
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("user_id", this.getUser().getId());
		List<Map<String, Object>> questionnaireList = questionnaireService.myQuestionnaire(param);
		model.addAttribute("questionnaireList", questionnaireList);
		return "information/questionnaire/wodehuodong";
	}
	
	
	/**
	 * 上一题
	 * @param quid 问卷ID
	 * @param num 第几题
	 * @param model
	 * @return
	 */
	@GetMapping("/myQuestionnaireDetails")
	String myQuestionnaireDetails(Integer quid,int num,Model model){
		//问卷
		QuestionnaireDO qu = questionnaireService.get(quid);
		if(qu != null){
			Map<String, Object> paramTop = new HashMap<String, Object>();
			paramTop.put("questionnaire_id", qu.getId());
			//题目
			List<TopicDO> topicList = topicService.list(paramTop);
			//1:第一次进入；2：最后一题  
			if(topicList.size() >= num){
				TopicDO topic = topicList.get(num-1);
				model.addAttribute("topic", topic);
				Map<String, Object> paramOption = new HashMap<String, Object>();
				paramOption.put("topic_id", topic.getId());
				//选项
				List<OptionDO> optionList = optionService.list(paramOption);
				System.out.println("查询题目选项"+optionList.size());
				model.addAttribute("optionList", optionList);
				Map<String, Object> paramQueAnswer = new HashMap<String, Object>();
				paramQueAnswer.put("user_id", this.getUserId());
				paramQueAnswer.put("topic_id", topic.getId());
				
				//0：单选；1：多选;2:填写
				if(topic.getIsRadio() == 2){
					QueAnswerDO queAnswer = queAnswerService.getQueAnswer(paramQueAnswer);
					model.addAttribute("queAnswer", queAnswer);//题目总数
				}else{
					List<QueAnswerDO> listQueAnswer = queAnswerService.getQueAnswerList(paramQueAnswer);
					model.addAttribute("listQueAnswer", listQueAnswer);//题目总数
				}
				
				
				
			}
			model.addAttribute("numTo", topicList.size());//题目总数
			model.addAttribute("num", num);//题目总数
			
		}
		return "information/questionnaire/getDiaochawenjuan";
	}
	@GetMapping("/myQuestionnaireDetailsNo")
	String myQuestionnaireDetailsNo(){
		return "information/questionnaire/diaochawenjuanNo";
	}
}
