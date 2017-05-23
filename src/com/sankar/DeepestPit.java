package com.sankar;

public class DeepestPit {

	public static void main(String[] args) {

		//int[] heights = { 0, 9, 6, -2, 7, 8, 0, -3, 2, 3 };
		int[] heights = { 0, 1, 3, -2, 0, 1, 0, -3, 2, 3 };
		int findDeepestPit = findDeepestPit(heights);
		System.out.println(findDeepestPit);
		int solution = solution(heights);
		System.out.println("THE DEEPEST PIT IS:"+solution);
	}

	public static int solution(int[] A) {

		int length = A.length;
		if (length < 3) {
			return -1;
		}
		int currentDepth = 0;
		int maxDepth = -1;
		int P, Q, R;
		int i, j, k;
		for (i = 0; i < length - 2; i++) {
			j = i + 1;

			if (A[i] > A[j]) {
				// The biggest P.
				P = A[i];
				while (j + 1 < length && A[j] > A[j + 1]) {
					j++;
				}
				// The smallest Q.
				Q = A[j];
				k = j + 1;
				while (k + 1 < length && A[k] < A[k + 1]) {
					k++;
				}
				if (k >= length) {
					break;
				}
				// The biggest R.
				R = A[k];

				currentDepth = (int) Math.min(P - Q, R - Q);
				if (currentDepth > maxDepth) {
					maxDepth = currentDepth;
				}
				i = k - 1;
			}
		}
		return maxDepth;
	}

	private static int findDeepestPit(int[] heights) {
		int firstIndex = 0;
		int deepest = -1;
		int depth = 0;
		boolean climbingUp = false;

		/*
		 * mark current position as highest (firstIndex) - go to next as long as
		 * we're going down - when we're not going down anymore, switch to mark
		 * us going up - go up until we can't go up anymore, then save the
		 * current depth of the pit, and mark the current position as highest
		 */

		for (int i = 0; i < heights.length - 1; i++) {
			int currentHeight = heights[i];
			int nextHeight = heights[i + 1];
			// find higher point
			if (!climbingUp) { // climbing down
				if (currentHeight < nextHeight) {
					// we can't go further down here
					climbingUp = true;
					deepest = i;
				}
			} else { // climbing up
				if (currentHeight > nextHeight) {
					// we can't get further up here.
					int lastIndex = i;
					int depthA = heights[firstIndex] - heights[deepest];
					int depthB = heights[lastIndex] - heights[deepest];
					int currDepth = Math.min(depthA, depthB);
					depth = Math.max(depth, currDepth);
					firstIndex = i;
					climbingUp = false;
				}
			}
		}

		int depthA = heights[firstIndex] - heights[deepest];
		int depthB = heights[heights.length - 1] - heights[deepest];
		int currDepth = Math.min(depthA, depthB);
		depth = Math.max(depth, currDepth);

		return depth;
	}
}