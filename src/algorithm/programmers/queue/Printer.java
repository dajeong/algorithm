package algorithm.programmers.queue;

import java.util.*;

/**
 * <b>문제 설명</b>
 * <p>일반적인 프린터는 인쇄 요청이 들어온 순서대로 인쇄합니다. 그렇기 때문에 중요한 문서가 나중에 인쇄될 수 있습니다. 이런 문제를 보완하기 위해 중요도가 높은 문서를 먼저 인쇄하는 프린터를 개발했습니다. 이 새롭게 개발한 프린터는 아래와 같은 방식으로 인쇄 작업을 수행합니다.<br><br>
 *
 * 1. 인쇄 대기목록의 가장 앞에 있는 문서(J)를 대기목록에서 꺼냅니다.<br>
 * 2. 나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면 J를 대기목록의 가장 마지막에 넣습니다.<br>
 * 3. 그렇지 않으면 J를 인쇄합니다.<br>
 * 예를 들어, 4개의 문서(A, B, C, D)가 순서대로 인쇄 대기목록에 있고 중요도가 2 1 3 2 라면 C D A B 순으로 인쇄하게 됩니다.<br><br>
 *
 * 내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 알고 싶습니다. 위의 예에서 C는 1번째로, A는 3번째로 인쇄됩니다.<br><br>
 *
 * 현재 대기목록에 있는 문서의 중요도가 순서대로 담긴 배열 priorities와 내가 인쇄를 요청한 문서가 현재 대기목록의 어떤 위치에 있는지를 알려주는 location이 매개변수로 주어질 때, 내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 return 하도록 solution 함수를 작성해주세요.</p>
 *
 * <b>제한사항</b>
 * <p>현재 대기목록에는 1개 이상 100개 이하의 문서가 있습니다.<br>
 * 인쇄 작업의 중요도는 1~9로 표현하며 숫자가 클수록 중요하다는 뜻입니다.<br>
 * location은 0 이상 (현재 대기목록에 있는 작업 수 - 1) 이하의 값을 가지며 대기목록의 가장 앞에 있으면 0, 두 번째에 있으면 1로 표현합니다.</p>
 * <p>
 * <b>입출력 예</b>
 *     <table>
 *         <tr>
 *             <td>priorities</td><td>location</td><td>return</td>
 *         </tr>
 *         <tr>
 *             <td>[2, 1, 3, 2]</td><td>2</td><td>1</td>
 *         </tr>
 *         <tr>
 *             <td>[1, 1, 9, 1, 1, 1]</td><td>0</td><td>5</td>
 *         </tr>
 *     </table>

 * 입출력 예 설명<br>
 * 예제 #1<br>
 * 문제에 나온 예와 같습니다.<br>
 * 예제 #2<br>
 * 6개의 문서(A, B, C, D, E, F)가 인쇄 대기목록에 있고 중요도가 1 1 9 1 1 1 이므로 C D E F A B 순으로 인쇄합니다.</p>
 */
public class Printer {

	/**
	 * 문제 '프린터' 풀이
	 * @param priorities
	 * @param location
	 * @return
	 */
	public int solution(int[] priorities, int location) {
		Queue<Map.Entry<Integer, Integer>> q = new LinkedList<>();
		for (int i = 0; i < priorities.length; i++) {
			q.add(new AbstractMap.SimpleEntry<>(i, priorities[i]));
		}
		int turn = 0;
		while (!q.isEmpty()) {
			Integer max = q.stream().max(Comparator.comparingInt(Map.Entry::getValue)).map(Map.Entry::getValue).get();
			Map.Entry<Integer, Integer> first = q.remove();
			if (max > first.getValue()) {
				q.add(first);
				continue;
			}
			turn++;
			if (first.getKey() == location) {
				return turn;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int solution = new Printer().solution(new int[]{2, 1, 3, 2}, 2);
		System.out.println(solution);
	}
}
