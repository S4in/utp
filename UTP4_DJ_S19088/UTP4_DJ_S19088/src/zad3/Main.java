/**
 *
 *  @author Dudek Jakub S19088
 *
 */

package zad3;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
  public static void main(String[] args) {

    Function<String, List<String>> flines = s -> {
      List<String> list = new ArrayList<String>();
      try {
        BufferedReader reader = new BufferedReader(new FileReader(s));

        String line;
        while ((line=reader.readLine())!=null){
          list.add(line);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      return list;
    };
    Function<List<String>,String> join = l -> {
      StringBuilder builder = new StringBuilder();
      for (String s:l) {
        builder.append(s);
      }
      return builder.toString();
    };
    Function<String,List<Integer>> collectInts = s ->{
      List<Integer> list = new ArrayList<Integer>();
      Pattern pattern = Pattern.compile("-?\\d+");
      Matcher matcher = pattern.matcher(s);
      while (matcher.find()){
        list.add(Integer.parseInt(matcher.group()));
      }
      return list;
    };
    Function<List<Integer>, Integer> sum = l -> {
      int result = 0;
      for (Integer i: l) {
        result += i;
      }
      return result;
    };

    

    String fname = System.getProperty("user.home") + "/LamComFile.txt"; 
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}
