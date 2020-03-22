package LeetCodeSolution.AlgorithmThought._06_Search._93_RestoreIPAddresses;

import java.util.ArrayList;
import java.util.List;

/*
 * 复原 IP 地址
 *
 * 题目描述：
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 *
 * 回溯算法的三个关键点：
 * 1. 选择（Choice）
 * 2. 约束（Constraints）
 * 3. 目标（Goal）
 *
 * 思路：
 * 1. 回溯。
 */
public class Solution {
    public static List<String> restoreIpAddresses(String s) {
        List<String> allIpAddress = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return allIpAddress;
        }

        int[] path = new int[4];
        dfs(allIpAddress, s, 0, path, 0);
        return allIpAddress;
    }

    /**
     * 搜索满足要求的所有的 IP 地址
     *
     * @param allIpAddress 所有满足要求的 IP 地址的结果集
     * @param s            给定的 IP 地址
     * @param builderIndex 该索引在给定的字符串 s 上向右滑动，其实就是指向字符串 s 中每个字符的指针
     * @param path         用于组成其中的一个符合要求的 IP 地址
     * @param segment      segment 可能的取值为 0、1、2、3
     */
    public static void dfs(List<String> allIpAddress, String s, int builderIndex, int[] path, int segment) {

        // base case，也就是我们的目标，由两部分构成
        // 1. catch answers，即得到的满足要求的情况
        // 2. 不满足要求的情况
        if (segment == 4 && builderIndex == s.length()) {
            allIpAddress.add(path[0] + "." + path[1] + "." + path[2] + "." + path[3]);
            // 对于两者不同时满足的情况，直接回溯，即 return
        } else if (segment == 4 || builderIndex == s.length()) {
            return;
        }

        for (int len = 1; len <= 3 && (builderIndex + len <= s.length()); len++) {
            String snapshot = s.substring(builderIndex, builderIndex + len);
            int value = Integer.parseInt(snapshot);
            // 约束
            if (value > 255 || len >= 2 && s.charAt(builderIndex) == '0') {
                break;
            }
            // 以下是我们的“选择”
            path[segment] = value;
            dfs(allIpAddress, s, builderIndex + len, path, segment + 1);
            path[segment] = -1;
        }
    }

    public static void main(String[] args) {
        String s = "25525511135";
        System.out.println(restoreIpAddresses(s));
    }
}
