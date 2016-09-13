package com.fnst.examination.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


import com.fnst.examination.entity.Question;
import com.fnst.examination.service.BaseServiceImpl;
import com.fnst.examination.service.ExamServiceImpl;

public class ExamUtil {
	private int difficulty ;
	private int questionType[] ;//选择填空简答  每种多少题
	private String knowledgeType[] ;// 知识点数组  输入有多少知识点
	private int knowledgeScale[] ;// 知识点分布 知识点比率
	private  Map<String, Integer[]> knowledgeType_id_map = new HashMap<String, Integer[]>();// 知识点作为key，value为对应的id数组
	private  Map<Integer, Integer> id_type_map = new HashMap<Integer, Integer>();// id和题目类型
	private  Map<Integer, Integer> id_difficulty_map = new HashMap<Integer, Integer>();// id和难度
	private  Map<Integer, String> id_knowledgeType_map = new HashMap<Integer, String>();// 记录筛选出的试题的id和知识点
	private  Map<Integer, String> id_context_map = new HashMap<Integer, String>();// id-内容
	private  Map<Integer, String> id_answer_map = new HashMap<Integer, String>();
	private  Map<String, ArrayList<String>> paperLast=new HashMap<String, ArrayList<String>>();
	private  Map<String, ArrayList<String>> answer=new HashMap<String, ArrayList<String>>();
	private ArrayList<Integer> testPaper = new ArrayList<Integer>();
	private ArrayList<Integer> testPaperBefore = new ArrayList<Integer>();
	private int difficultyCount[] = { 0, 0, 0, 0, 0 };
	private int total;
	private int correlateNeed;
	private int knowledgeCount[];
	private ArrayList<String>array1;
	private ArrayList<String>array2;
	private ArrayList<String>array3;
	private ArrayList<String>ans1;
	private ArrayList<String>ans2;
	private ArrayList<String>ans3;
	private boolean getQuestionsOk=false;
	private boolean difficultyOk=false;
	private boolean repeatOk=false;

	public void getUserInput(int difficulty_input, int questionType_input[],
			String knowledgeType_input[], int knowledgeScale_input[],int correlateNeed_input) {
		difficulty = difficulty_input;
		correlateNeed=correlateNeed_input;
		questionType = new int[questionType_input.length];
		knowledgeType=new String[knowledgeType_input.length];
		knowledgeScale=new int[knowledgeScale_input.length];
		for (int i = 0; i < questionType_input.length; ++i) {
			// 赋值
			questionType[i]=questionType_input[i];
		}
		for (int i = 0; i < knowledgeType_input.length; ++i) {
			// 赋值
			knowledgeType[i]=knowledgeType_input[i];
		}
		for (int i = 0; i < knowledgeScale_input.length; ++i) {
			// 赋值
			knowledgeScale[i]=knowledgeScale_input[i];
		}
	}

	private void paperPrepare() {
		total = 10;
		//ArrayList knowledgeCount = new ArrayList();
		// 比率数组换为数量
		knowledgeCount=new int[knowledgeScale.length];
		for (int i = 0; i < knowledgeScale.length; ++i) {
			knowledgeCount[i] =  (knowledgeScale[i] * total)/100;
		}
		Integer a[]=new BaseServiceImpl().getExamId();
		for(int i=0;i<a.length;++i){
			System.out.println("---------------2"+a[i]);
			testPaperBefore.add(a[i]);
			System.out.println("================");
		}
		getQuestionMap();
	}

/*
 * 数据取入id_difficulty_map，id_type_map，id_knowledgeType_map
 */
	private void getQuestionMap() {

		List<Question> questList = new ExamServiceImpl().findAllQuestion();
		for(int i=0;i<questList.size();++i){
			int id=questList.get(i).getId();
			System.out.println(questList.get(i).getLevel()+"saddad");
			int diff=Integer.parseInt(questList.get(i).getLevel().replaceAll("[\\n\\r]*",""));
			int type=Integer.parseInt(questList.get(i).getType().replaceAll("[\\n\\r]*",""));
			String context=questList.get(i).getContext();
			String answer=questList.get(i).getAnswer();
			id_type_map.put(id, type);
			id_difficulty_map.put(id, diff+1);
			id_context_map.put(id, context);
			id_answer_map.put(id, answer);
		}
		ArrayList<Integer> a=new ArrayList<Integer>();
		for(int i=0;i<knowledgeType.length;++i){
			a.clear();
			for(int j=0;j<questList.size();++j){
				if(questList.get(j).getPoint().indexOf(knowledgeType[i])>-1){
					a.add(questList.get(j).getId());
				}
			}
			Integer[] s=new Integer[a.size()];
			for(int k=0;k<a.size();++k){
				s[k]=a.get(k);
			}
			knowledgeType_id_map.put(knowledgeType[i], s);
		}
	
	}

	private void getQuestions() {
System.out.println("===============getq");
		while (questionType[0] > 0 && questionType[1] > 0
				&& questionType[2] > 0) {
			System.out.println("===============inner while");
			// 知识点轮流取满
			for (int i = 0; i < knowledgeType.length; i++) {
				System.out.println(questionType.toString());
				System.out.println(knowledgeCount.toString());
				if (0 == knowledgeCount[i]) {
					System.out.println("===============1");
					continue;
				}
				Integer knowledgeType_id[] = knowledgeType_id_map
						.get(knowledgeType[i]);
				while (knowledgeCount[i] > 0) {
					System.out.println("===============2");
					int num = new Random().nextInt(knowledgeType_id.length);// 随机数
					int id = knowledgeType_id[num];// 题目id
					if(testPaper.contains(id))continue;
					int type = id_type_map.get(id);// 题目类型
					if (questionType[type] > 0) {// 此类型题目还需要
						questionType[type]--;
						knowledgeCount[i]--;
						testPaper.add(id);// 取得合适题目，存入试卷
						difficultyCount[id_difficulty_map.get(id)-1]++;// 统计各数量难度
						id_knowledgeType_map.put(id, knowledgeType[i]);
					} else
						System.out.println("===============3");
						// 此题目达到上限，重取题目
						continue;
				}
			}

		}	
		getQuestionsOk=true;
	}

	/*
	 * 难度调整
	 */
	private void difficultyChange(int diff) {
		int max = total / difficulty+1;
		int min = total / difficulty / 5 * 4;// 每种难度最小值

		int index = 0;
		int id_change;
		int difficulty_change;
		int type_change;
		// int
		// diffcultyTotal=difficultyCount][0]+2*difficultyCount[1]+3*difficultyCount[2]+4*difficultyCount[3]+5*difficultyCount[4];
		switch (diff) {
		case 1:
			// 全为难度1的题目，doNothing
			break;
		// 平衡难度2题目数量
		case 2:
			//difficulty2 = difficultyCount[1];

			while (difficultyCount[1] < min) {
				int difficulty_before = 2;
				int id_before = 0;
				// 找试卷中难度不为2的题目
				while (2 == difficulty_before) {
					index = new Random().nextInt(testPaper.size());
					id_before = testPaper.get(index);
					difficulty_before = id_difficulty_map.get(id_before);
				}
				int type_before = id_type_map.get(id_before);
				String knowledgeType=id_knowledgeType_map.get(id_before);
				Integer knowledgeType_id[] = knowledgeType_id_map
						.get(knowledgeType);
				// 找题库中难度为2的同类型的题目
				int num=0;
				int random=knowledgeType_id.length;
					do {
						 num = new Random().nextInt(random);// 随机数
						id_change = knowledgeType_id[num];// 题目id
						difficulty_change = id_difficulty_map.get(id_change);
						type_change = id_type_map.get(id_change);

						System.out.println(testPaper);

					} while (difficulty_change != 2||testPaper.contains(id_change)
							|| type_before != type_change );
					testPaper.remove(index);
					testPaper.add(id_change);
					difficultyCount[difficulty_before - 1]--;
					difficultyCount[1]++;
					id_knowledgeType_map.remove(id_before);
					id_knowledgeType_map.put(id_change,knowledgeType);
			}
			while (difficultyCount[1] > max) {
				int difficulty_before = 0;
				int id_before = 0;
				// 找试卷中难度为2的题目
				while (2 != difficulty_before) {
					index = new Random().nextInt(testPaper.size());
					id_before = testPaper.get(index);
					difficulty_before = id_difficulty_map.get(id_before);
				}
				int type_before = id_type_map.get(id_before);
				String knowledgeType=id_knowledgeType_map.get(id_before);
				Integer knowledgeType_id[] = knowledgeType_id_map
						.get(knowledgeType);
				// 找题库中难度不为2的同类型的题目
				do {
					int num = new Random().nextInt(knowledgeType_id.length);// 随机数
					id_change = knowledgeType_id[num];// 题目id
					difficulty_change = id_difficulty_map.get(id_change);
					type_change = id_type_map.get(id_change);
					System.out.println(12);
				} while (testPaper.contains(id_change)
						|| type_before != type_change || difficulty_change == 2);
				testPaper.remove(index);
				testPaper.add(id_change);
				difficultyCount[difficulty_change - 1]++;
				difficultyCount[1]--;
				id_knowledgeType_map.remove(id_before);
				id_knowledgeType_map.put(id_change, knowledgeType);
			}
			break;
		case 3:

			difficultyChange(2);// 平衡难度2题目数量；
	//		difficulty3 = difficultyCount[2];
			while (difficultyCount[2] < min) {
				int difficulty_before = 2;
				int id_before = 0;
				// 找试卷中难度不为2和3的题目
				while (3 == difficulty_before || 2 == difficulty_before) {
					index = new Random().nextInt(testPaper.size());
					id_before = testPaper.get(index);
					difficulty_before = id_difficulty_map.get(id_before);
				}
				int type_before = id_type_map.get(id_before);
				String knowledgeType=id_knowledgeType_map.get(id_before);
				Integer knowledgeType_id[] = knowledgeType_id_map
						.get(knowledgeType);
				// 找题库中难度为3的同类型的题目
				do {
					int num = new Random().nextInt(knowledgeType_id.length);// 随机数
					id_change = knowledgeType_id[num];// 题目id
					difficulty_change = id_difficulty_map.get(id_change);
					type_change = id_type_map.get(id_change);
					System.out.println(21);
				} while (testPaper.contains(id_change)
						|| type_before != type_change || difficulty_change != 3);
				testPaper.remove(index);
				testPaper.add(id_change);
				difficultyCount[difficulty_before - 1]--;
				difficultyCount[2]++;
				id_knowledgeType_map.remove(id_before);
				id_knowledgeType_map.put(id_change, knowledgeType);
			}
			while (difficultyCount[2] > max) {
				int difficulty_before = 0;
				int id_before = 0;
				// 找试卷中难度为3的题目
				while (3 != difficulty_before) {
					index = new Random().nextInt(testPaper.size());
					id_before = testPaper.get(index);
					difficulty_before = id_difficulty_map.get(id_before);
				}
				int type_before = id_type_map.get(id_before);
				String knowledgeType=id_knowledgeType_map.get(id_before);
				Integer knowledgeType_id[] = knowledgeType_id_map
						.get(knowledgeType);
				// 找题库中难度不为2和3的同类型的题目
				do {
					int num = new Random().nextInt(knowledgeType_id.length);// 随机数
					id_change = knowledgeType_id[num];// 题目id
					difficulty_change = id_difficulty_map.get(id_change);
					type_change = id_type_map.get(id_change);
					System.out.println(22);
				} while (testPaper.contains(id_change)
						|| type_before == type_change || difficulty_change == 2
						|| difficulty_change == 3);
				testPaper.remove(index);
				testPaper.add(id_change);
				difficultyCount[difficulty_change - 1]++;
				difficultyCount[2]--;
				id_knowledgeType_map.remove(id_before);
				id_knowledgeType_map.put(id_change, knowledgeType);
			}

			break;
		case 4:
			difficultyChange(3);// 平衡2，3难度数量
			//difficulty4 = difficultyCount[3];
			while (difficultyCount[3] < min) {
				int difficulty_before = 2;
				int id_before = 0;
				// 找试卷中难度不为2和3和4的题目
				while (4 == difficulty_before || 3 == difficulty_before
						|| 2 == difficulty_before) {
					index = new Random().nextInt(testPaper.size());
					id_before = testPaper.get(index);
					difficulty_before = id_difficulty_map.get(id_before);
				}
				int type_before = id_type_map.get(id_before);
				String knowledgeType=id_knowledgeType_map.get(id_before);
				Integer knowledgeType_id[] = knowledgeType_id_map
						.get(knowledgeType);
				// 找题库中难度为4的同类型的题目
				do {
					int num = new Random().nextInt(knowledgeType_id.length);// 随机数
					id_change = knowledgeType_id[num];// 题目id
					difficulty_change = id_difficulty_map.get(id_change);
					type_change = id_type_map.get(id_change);
					System.out.println(31);
				} while (testPaper.contains(id_change)
						|| type_before == type_change || difficulty_change != 4);
				testPaper.remove(index);
				testPaper.add(id_change);
				difficultyCount[difficulty_before - 1]--;
				difficultyCount[3]++;
				id_knowledgeType_map.remove(id_before);
				id_knowledgeType_map.put(id_change, knowledgeType);
			}
			while (difficultyCount[3] > max) {
				int difficulty_before = 0;
				int id_before = 0;
				// 找试卷中难度为4的题目
				while (4 != difficulty_before) {
					index = new Random().nextInt(testPaper.size());
					id_before = testPaper.get(index);
					difficulty_before = id_difficulty_map.get(id_before);
				}
				int type_before = id_type_map.get(id_before);
				String knowledgeType=id_knowledgeType_map.get(id_before);
				Integer knowledgeType_id[] = knowledgeType_id_map
						.get(knowledgeType);
				// 找题库中难度不为2和3和4的同类型的题目
				do {
					int num = new Random().nextInt(knowledgeType_id.length);// 随机数
					id_change = knowledgeType_id[num];// 题目id
					difficulty_change = id_difficulty_map.get(id_change);
					type_change = id_type_map.get(id_change);
					System.out.println(32);
				} while (testPaper.contains(id_change)
						|| type_before != type_change || difficulty_change == 2
						|| difficulty_change == 3 || difficulty_change == 4);
				testPaper.remove(index);
				testPaper.add(id_change);
				difficultyCount[difficulty_before - 1]++;
				difficultyCount[3]--;
				id_knowledgeType_map.remove(id_before);
				id_knowledgeType_map.put(id_change, knowledgeType);
			}
			break;
		case 5:
			difficultyChange(4);// 2,3,4已平衡
			//difficulty5 = difficultyCount[4];
			while (difficultyCount[4] < min) {
				int difficulty_before = 2;
				int id_before = 0;
				// 找试卷中难度为1的题目
				while (1 != difficulty_before) {
					index = new Random().nextInt(testPaper.size());
					id_before = testPaper.get(index);
					difficulty_before = id_difficulty_map.get(id_before);
				}
				int type_before = id_type_map.get(id_before);
				String knowledgeType=id_knowledgeType_map.get(id_before);
				Integer knowledgeType_id[] = knowledgeType_id_map
						.get(knowledgeType);
				// 找题库中难度为5的同类型的题目
				do {
					int num = new Random().nextInt(knowledgeType_id.length);// 随机数
					id_change = knowledgeType_id[num];// 题目id
					difficulty_change = id_difficulty_map.get(id_change);
					type_change = id_type_map.get(id_change);

				} while (testPaper.contains(id_change)
						|| type_before == type_change || difficulty_change != 5);
				testPaper.remove(index);
				testPaper.add(id_change);
				difficultyCount[difficulty_before - 1]--;
				difficultyCount[4]++;
				id_knowledgeType_map.remove(id_before);
				id_knowledgeType_map.put(id_change, knowledgeType);
			}
			while (difficultyCount[4] > max) {
				int difficulty_before = 0;
				int id_before = 0;
				// 找试卷中难度为5的题目
				while (4 != difficulty_before) {
					index = new Random().nextInt(testPaper.size());
					id_before = testPaper.get(index);
					difficulty_before = id_difficulty_map.get(id_before);
				}
				int type_before = id_type_map.get(id_before);
				String knowledgeType=id_knowledgeType_map.get(id_before);
				Integer knowledgeType_id[] = knowledgeType_id_map
						.get(knowledgeType);
				// 找题库中难度为1的同类型的题目
				do {
					int num = new Random().nextInt(knowledgeType_id.length);// 随机数
					id_change = knowledgeType_id[num];// 题目id
					difficulty_change = id_difficulty_map.get(id_change);
					type_change = id_type_map.get(id_change);

				} while (testPaper.contains(id_change)
						|| type_before == type_change || difficulty_change != 1);
				testPaper.remove(index);
				testPaper.add(id_change);
				difficultyCount[0]++;
				difficultyCount[4]--;
				id_knowledgeType_map.remove(id_before);
				id_knowledgeType_map.put(id_change, knowledgeType);
			}

			break;
		default:
			break;
		}difficultyOk=true;
	}

	private void repeatJudge(ArrayList<Integer> testLastYear) {
		if(null==testLastYear)return;
		System.out.println("repeatJudge");
		ArrayList<Integer> a = new ArrayList<>(testPaper);
		a.removeAll(testLastYear);// 将上年试卷所有今年试题移除
		int correlateNeedCount = (int) (testPaper.size() * correlateNeed);// 要求的不能超过的重复题数量
		int correlateCount = testPaper.size() - a.size();

		if (correlateCount < correlateNeedCount) {// 满足

		} else {
			int needChange = correlateCount - correlateNeedCount;//需要替换的重复项数量
			ArrayList<Integer> repeat = new ArrayList<>(testPaper);
			repeat.removeAll(a);//重复项
			for (int i = 0; i < needChange; ++i) {
				int id_before = repeat.get(i);
				int difficulty_before = id_difficulty_map.get(id_before);
				int type_before = id_type_map.get(id_before);
				int id_change;
				int difficulty_change;
				int type_change;
				Integer knowledgeType_id[] = knowledgeType_id_map
						.get(id_knowledgeType_map.get(id_before));//此题难度对应的数组
				do {
					int num = new Random().nextInt(knowledgeType_id.length);// 随机数
					id_change = knowledgeType_id[num];// 题目id
					difficulty_change = id_difficulty_map.get(id_change);
					type_change = id_type_map.get(id_change);

				} while (testPaper.contains(id_change)
						|| type_before != type_change
						|| difficulty_before != difficulty_change);
					testPaper.remove(new Integer(id_before));
					testPaper.add(id_change);
			}
		
		}
		repeatOk=true;
	}
	public ArrayList<Integer> createPaperA(final int difficulty_input, final int questionType_input[],
			final String knowledgeType_input[], final int knowledgeScale_input[],final int correlateNeed_input){
			Thread t=new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("inner thread");
				getUserInput(difficulty_input, questionType_input, knowledgeType_input, knowledgeScale_input, correlateNeed_input);
				
				System.out.println("after input");
				paperPrepare();
				System.out.println("after prepare");
				getQuestions();
				System.out.println("after get");
				difficulty++;
				difficultyChange(difficulty);
				System.out.println("after change");
				repeatJudge( testPaperBefore);
				System.out.println("after judge");
				//System.out.println("t");
				super.run();
			}
		};
		t.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(getQuestionsOk);System.out.println(difficultyOk);System.out.println(repeatOk);
		if(!getQuestionsOk||!difficultyOk||!repeatOk){
			//t.interrupt();
			t.stop();
			return null;
		}
		
		return testPaper;
		
	}
	/*
	 * b卷生成,难度调整之后金额使用，替换题目生成b卷
	 * TODO
	 */
	private void createPaperB(){
		//
		
		
		
	}
	public String getRepeat(){
		if(null==testPaperBefore)return 0+"";
		//System.out.println("repeatJudge");
		ArrayList<Integer> a = new ArrayList<>(testPaper);
		a.removeAll(testPaperBefore);// 将上年试卷所有今年试题移除
		int correlateNeedCount = (int) (testPaper.size() * correlateNeed);// 要求的不能超过的重复题数量
		int correlateCount = testPaper.size() - a.size();
		double d=correlateCount/testPaper.size();
		return d+"";
	}
	/*
	 * 根据id生成答案
	 */
	public Map<String, ArrayList<String>>getAnswer(){
		return answer;
	}
	/*
	 * 输出并存储
	 */
	public Map<String, ArrayList<String>> getPaperLast(){
		array1=new ArrayList<>();
		array2=new ArrayList<>();
		array3=new ArrayList<>();
		for(int i=0;i<testPaper.size();++i){
			int id=testPaper.get(i);
			int type=id_type_map.get(id);
			String context=id_context_map.get(id);
			switch (type) {
			case 0:
				array1.add(context);
				break;
			case 1:
				array2.add(context);
				break;
			case 2:
				array3.add(context);
				break;

			default:
				break;
			}
		}
		for(int i=0;i<array1.size();++i){
			array1.set(i, 1+i+"."+array1.get(i));
		}
		for(int i=0;i<array2.size();++i){
			array2.set(i, array1.size()+1+i+"."+array2.get(i));
		}
		for(int i=0;i<array3.size();++i){
			array3.set(i,array1.size()+ array2.size()+1+i+"."+array3.get(i));
		}
		paperLast.put("vacantList", array1);
		paperLast.put("choiceList", array2);
		paperLast.put("questList", array3);
		
		return paperLast;
	}
	public Map<String, ArrayList<String>> getAnswerLast(){
		array1=new ArrayList<>();
		array2=new ArrayList<>();
		array3=new ArrayList<>();
		for(int i=0;i<testPaper.size();++i){
			int id=testPaper.get(i);
			int type=id_type_map.get(id);
			String answer=id_answer_map.get(id);
			switch (type) {
			case 0:
				array1.add(answer);
				break;
			case 1:
				array2.add(answer);
				break;
			case 2:
				array3.add(answer);
				break;

			default:
				break;
			}
		}
		for(int i=0;i<array1.size();++i){
			array1.set(i, 1+i+"."+array1.get(i));
		}
		for(int i=0;i<array2.size();++i){
			array2.set(i, array1.size()+1+i+"."+array2.get(i));
		}
		for(int i=0;i<array3.size();++i){
			array3.set(i,array1.size()+ array2.size()+1+i+"."+array3.get(i));
		}
		answer.put("vacantAnswer", array1);
		answer.put("choiceAnswer", array2);
		answer.put("questAnswer", array3);
		
		return answer;
	}

}