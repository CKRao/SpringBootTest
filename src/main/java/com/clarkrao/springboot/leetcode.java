package com.clarkrao.springboot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ClarkRao
 * @Date: 2019/4/7 23:23
 * @Description:
 */
public class leetcode {

    public static void main(String[] args) {
        int[] nums = {3,2,4};
        int target = 6;
        System.out.println(new leetcode().twoSum1(nums, target));
    }

    /**
     *给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标.
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     *
     * 示例:
     * 给定: nums = [2, 7, 11, 15], target = 9
     *
     *  因为 nums[0] + nums[1] = 2 + 7 = 9
     *  所以返回 [0, 1]
     */
    public int[] twoSum(int[] nums, int target) {
        List<Integer> otherNums = new ArrayList<>();
        int i, index = 0;
        for(i = 0; i < nums.length - 1; i++){
            otherNums.add(target - nums[i]);
            index = otherNums.indexOf(nums[i + 1]);
            if(index != -1){
                break;
            }
        }
        return new int[]{index, i + 1};
    }

    public int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int anotherNum = target - nums[i];

            if (map.get(anotherNum) != null) {
                return new int[]{map.get(anotherNum), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }
}
