
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Lewis Shemery
 * 
 * This is my version of the board game mastermind, where the user makes the code and the computer
 * guesses. I first generate an array list of string arrays of all possible guesses from 1111-6666.
 * The user is then prompted to enter their secret code which is stored in a string array called 
 * guess. I then used Donald Knuth's algorithm to compare each index to every other index which finds 
 * the guess that will remove the most amount of guesses. This will always be 1122 for the first guess.
 * 1122 represents the  lowest numerical value that will leave the minimum guesses remaining; 256 after 
 * the first guess). The all_guesses array is then modify and the count_max function is ran again. 
 * Mathematically, the answer will always be guessed in 5 turns or less.
 */
public class mastermindai {

    public static void main(String[] args) {
        ArrayList<String[]> all_guesses = all_possible_guesses();
        String answer = user_answer();
        int turn_counter = 1;
        while (!Arrays.equals(string_to_array(answer), all_guesses.get(0))) {
            String[] guess = next_guess(all_guesses);
            System.out.printf("\nTurn %d.\n\nMy guess is: %s.\n", turn_counter, Arrays.toString(guess));
            System.out.printf("code: %s\n\n", answer);
            Integer[] user_pegs = user_response(string_to_array(answer), guess);
            modify_guesses(guess, all_guesses, user_pegs);
            //when the computer gets 4 black pegs, it wins.
            if (user_pegs[0] == 4) {
                break;
            }
            turn_counter++;
        }
        System.out.printf("\nYour code is: %s\n", Arrays.toString(all_guesses.get(0)));
        System.out.printf("Beep boop, get wrecked.\n"
                + "It only took me %d turns.\n", turn_counter);
    }

    public static String[] next_guess(ArrayList<String[]> all_guesses) {
        //determines the next guess by finding the mimum result of the count_max function
        int min = 300;
        String[] nextGuess = new String[4];
        for (int i = 0; i < all_guesses.size(); i++) {
            int max = count_max(all_guesses.get(i), all_guesses);
            if (min > max) {
                min = max;
                nextGuess = all_guesses.get(i);
            }
        }
        return nextGuess;
    }

    public static Integer[] count_pegs(String[] answer, String[] guess) {
        //used to modify the all_guesses array and as input validation for user_pegs
        //pegs are stored in an integer array as [black,white]
        Integer[] pegs = {0, 0};
        String[] temp_answer = Arrays.copyOf(answer, answer.length);
        String[] temp_guess = Arrays.copyOf(guess, guess.length);
        for (int i = 0; i < temp_guess.length; i++) {
            if (temp_answer[i].equals(temp_guess[i])) {
                temp_guess[i] = "o";
                temp_answer[i] = "x";
                pegs[0]++;
            }
        }
        for (int i = 0; i < temp_guess.length; i++) {
            for (int j = 0; j < temp_answer.length; j++) {
                if (temp_guess[i].equals(temp_answer[j])) {
                    temp_guess[i] = "o";
                    temp_answer[j] = "x";
                    pegs[1]++;
                }
            }
        }
        return pegs;
    }

    public static void modify_guesses(String[] guess, ArrayList<String[]> all_guesses, Integer[] user_pegs) {
        //modifies the all_guesses array by removing any guess that doesn't produce the same count_pegs array
        //as the user_pegs array when guess is treated as the answer. this works off the principle:
        //if x=y and y=z, then x=z
        int possibleGuesses = 0;
        while (all_guesses.size() > possibleGuesses) {
            Integer[] computer_pegs = count_pegs(guess, all_guesses.get(possibleGuesses));
            if (!Arrays.equals(user_pegs, computer_pegs)) {
                all_guesses.remove(possibleGuesses);
            } else {
                possibleGuesses++;
            }
        }
    }

    public static int count_max(String[] answer, ArrayList<String[]> all_guesses) {
        //implements counters which count the maximum possible remaining guesses for all possible combinations of pegs
        int a = 0; //....
        int b = 0; //w...
        int c = 0; //b...
        int d = 0; //ww..
        int e = 0; //bw..
        int f = 0; //bb..
        int g = 0; //www.
        int h = 0; //bww.
        int i = 0; //bbw.
        int j = 0; //bbb.
        int k = 0; //wwww
        int l = 0; //bwww
        int m = 0; //bbww
        int n = 0; //bbbb

        Integer[] A = {0, 0};
        Integer[] B = {0, 1};
        Integer[] C = {1, 0};
        Integer[] D = {0, 2};
        Integer[] E = {1, 1};
        Integer[] F = {2, 0};
        Integer[] G = {0, 3};
        Integer[] H = {1, 2};
        Integer[] I = {2, 1};
        Integer[] J = {3, 0};
        Integer[] K = {0, 4};
        Integer[] L = {1, 3};
        Integer[] M = {2, 2};
        Integer[] N = {4, 0};

        int max = 0;
        //compares the pegs of the variable treated as the answer and each guess remaining in the guess array
        //if they are equal it increments the variable that represents the combination of pegs that caused the if statement to be true
        for (int p = 0; p < all_guesses.size(); p++) {
            Integer[] pegs = count_pegs(answer, all_guesses.get(p));

            if (Arrays.equals(pegs, A)) {
                a++;
                if (a > max) {
                    max = a;
                }
            }
            if (Arrays.equals(pegs, B)) {
                b++;
                if (b > max) {
                    max = b;
                }
            }
            if (Arrays.equals(pegs, C)) {
                c++;
                if (c > max) {
                    max = c;
                }
            }
            if (Arrays.equals(pegs, D)) {
                d++;
                if (d > max) {
                    max = d;
                }
            }
            if (Arrays.equals(pegs, E)) {
                e++;
                if (e > max) {
                    max = e;
                }
            }
            if (Arrays.equals(pegs, F)) {
                f++;
                if (f > max) {
                    max = f;
                }
            }
            if (Arrays.equals(pegs, G)) {
                g++;
                if (g > max) {
                    max = g;
                }
            }
            if (Arrays.equals(pegs, H)) {
                h++;
                if (h > max) {
                    max = h;
                }
            }
            if (Arrays.equals(pegs, I)) {
                i++;
                if (i > max) {
                    max = i;
                }
            }
            if (Arrays.equals(pegs, J)) {
                j++;
                if (j > max) {
                    max = j;
                }
            }
            if (Arrays.equals(pegs, K)) {
                k++;
                if (k > max) {
                    max = k;
                }
            }
            if (Arrays.equals(pegs, L)) {
                l++;
                if (l > max) {
                    max = l;
                }
            }
            if (Arrays.equals(pegs, M)) {
                m++;
                if (m > max) {
                    max = m;
                }
            }
            if (Arrays.equals(pegs, N)) {
                n++;
                if (n > max) {
                    max = n;
                }
            }
        }
        return max;
    }

    public static Integer[] user_response(String[] answer, String[] guess) {
        //gets the peg count from the user for the computers guess compared to the users code
        //if the user inputs the pegs wrong the if statement will return true and return the real peg count
        Scanner input = new Scanner(System.in);
        Integer[] pegs = new Integer[2];
        System.out.printf(" Black pegs: ");
        pegs[0] = input.nextInt();
        System.out.printf(" White pegs: ");
        pegs[1] = input.nextInt();
        if (!Arrays.equals(count_pegs(answer, guess), pegs)) {
            System.out.printf("\n\nI can't let you do that dave...\nYou enterd the wrong number of pegs so I entered the right ones for you.\n");
            Integer[] realPegs = count_pegs(answer, guess);
            System.out.printf("Black pegs: %d\n", realPegs[0]);
            System.out.printf("White pegs: %d\n", realPegs[1]);
            return realPegs;
        }
        return pegs;
    }

   public static ArrayList<String[]> all_possible_guesses() {
        //this generates the array of all possible guesses from 1111 to 6666 where each digit is from 1 to 6,
        //including 1 and 6(essentially base 6 shifted one number to the right on a number line.
        ArrayList<String[]> all_guesses = new ArrayList<>();
        for (int a = 0; a < 6; a++) {
            for (int b = 0; b < 6; b++) {
                for (int c = 0; c < 6; c++) {
                    for (int d = 0; d < 6; d++) {
                        String[] guess = new String[4];
                        guess[0] = Integer.toString(a + 1);
                        guess[1] = Integer.toString(b + 1);
                        guess[2] = Integer.toString(c + 1);
                        guess[3] = Integer.toString(d + 1);
                        all_guesses.add(guess);
                    }
                }
            }
        }
        return all_guesses;
    } 

    public static String user_answer() {
        //prompts the user to enter their secret code. If the input is invalid the function calls itself again.
        Scanner input = new Scanner(System.in);
        System.out.printf("Please enter the secret code: ");
        String answer = input.next();
        if (answer.equals("q")) {
            System.exit(0);
        }
        if (validate_answer(answer) == false) {
            System.out.printf("\n\nERROR: You can only enter 4 consecutive integers\n"
                    + "ranging from 1-6.\n\n");
            return user_answer();
        }
        return answer;
    }

    public static boolean validate_answer(String answer) {
        //used for input validation
        if (answer.length() != 4) {
            return false;
        }
        for (int i = 0; i < answer.length(); i++) {
            char c = answer.charAt(i);
            if ("123456".indexOf(c) < 0) {
                return false;
            }
        }

        return true;
    }

    public static int counter = 0;

    public static boolean turn_counter() {
        //if you need to read a comment to understand this, go fuck yourself.
        counter++;
        if (counter > 10) {
            return false;
        } else {
            return true;
        }
    }

    public static String[] string_to_array(String answer) {
        //eat shit and die faggot.
        String[] result = new String[answer.length()];
        for (int i = 0; i < answer.length(); i++) {
            result[i] = answer.substring(i, i + 1);
        }
        return result;
    }
}
