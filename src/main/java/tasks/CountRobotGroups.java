package tasks;

import common.Difficulty;
import common.LeetCode;

import java.util.*;

/**
 * @author Ruslan Rakhmedov
 * @created 2026-09-13
 */
@LeetCode(
        id = 4045,
        name = "Count Robot Groups",
        url = "https://leetcode.com/problems/count-robot-groups/description/",
        difficulty = Difficulty.MEDIUM
)
public class CountRobotGroups {
    class Robot {
        int leftPosition;
        int position;
        int speed;
        double mergeTime;

        public Robot(int leftPosition, int position, int speed, double mergeTime) {
            this.leftPosition = leftPosition;
            this.position = position;
            this.speed = speed;
            this.mergeTime = mergeTime;
        }
    }

    public int countGroups(int[] positions, int[] speeds, int maxDistanceAllowed) {
        LinkedList<Robot> list = new LinkedList<>();
        for (int i = positions.length - 1; i >= 0; i--) {
            int leftPosition = positions[i];
            int position = positions[i];
            int speed = speeds[i];
            double mergeTime = Double.MAX_VALUE;

            while (!list.isEmpty()) {
                Robot robot = list.getLast();
                int distance = robot.leftPosition - position;
                if (distance <= maxDistanceAllowed) {
                    position = robot.position;
                    speed = robot.speed;
                    mergeTime = robot.mergeTime;
                    list.removeLast();
                } else if (speed <= robot.speed) {
                    break;
                } else {
                    double requiredTime = (double) (distance - maxDistanceAllowed) / (speed - robot.speed);
                    if (requiredTime <= robot.mergeTime) {
                        position = robot.position;
                        speed = robot.speed;
                        mergeTime = robot.mergeTime;
                        list.removeLast();
                    } else {
                        break;
                    }
                }
            }

            list.addLast(new Robot(leftPosition, position, speed, mergeTime));
        }
        return list.size();
    }
}