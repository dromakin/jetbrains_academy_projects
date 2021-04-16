package cinema;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Cinema {
    static ArrayList<Integer> seat_number = new ArrayList<>();
    static ArrayList<Integer> rows = new ArrayList<>();

    static int tickets_lots;
    static float percentage;
    static int current_income;
    static int total_income;

    public static void show_all_seats(int a, int b) {
        System.out.println();
        System.out.println("Cinema:");
        for (int i = 0; i <= a; i++) {
            if (i == 0) {
                String str = "  ";
                for (int j = 1; j <= b; j++) {
                    if (j != b) {
                        str += j + " ";
                    } else {
                        str += j;
                    }
                }
                System.out.println(str);
            } else {
                String str = i + " ";
                for (int j = 0; j < b; j++) {
                    if (j != b - 1) {
                        str += "S ";
                    } else {
                        str += "S";
                    }
                }
                System.out.println(str);
            }
        }
        System.out.println();
    }

    public static boolean check_row_seat_number(int a1, int b1) {
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i) == a1 && seat_number.get(i) == b1) {
                return true;
            }
        }
        return false;
    }

    public static void show_seats(int a, int b) {
        if (rows.size() == 0) {
            show_all_seats(a, b);
        } else {
            System.out.println();
            System.out.println("Cinema:");
            for (int i = 0; i <= a; i++) {
                if (i == 0) {
                    String str = "  ";
                    for (int j = 1; j <= b; j++) {
                        if (j != b) {
                            str += j + " ";
                        } else {
                            str += j;
                        }
                    }
                    System.out.println(str);
                } else {
                    String str = i + " ";
                    for (int j = 0; j < b; j++) {
                        if (check_row_seat_number(i, j + 1)) {
                            if (j != b - 1) {
                                str += "B ";
                            } else {
                                str += "B";
                            }
                        } else if (j != b - 1) {
                            str += "S ";
                        } else {
                            str += "S";
                        }
                    }
                    System.out.println(str);
                }
            }
            System.out.println();
        }
    }

    public static void set_total_income(int a, int b) {
        if (a * b > 60) {
            int row1 = (int) (a / 2);
            int row2 = a - row1;
            total_income = (row1 * 10 + row2 * 8) * b;
        } else {
            total_income = a * b * 10;
        }
    }

    public static void show_statistic() {
        System.out.println("Number of purchased tickets: " + tickets_lots);
        System.out.printf("Percentage: %.2f%s\n", percentage, "%");
//        DecimalFormat df = new DecimalFormat("##.##");
//        System.out.println("Percentage: " + df.format(percentage) + "%");
        System.out.println("Current income: $" + current_income);
        System.out.println("Total income: $" + total_income);
        System.out.println();
    }

    public static void buy_tickets(int a, int b) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println();

        while (true) {
            System.out.println("Enter a row number:");
            int a1 = Integer.parseInt(reader.readLine());
            System.out.println("Enter a seat number in that row:");
            int b1 = Integer.parseInt(reader.readLine());

            if (rows.contains(a1) && seat_number.contains(b1)
                    && seat_number.indexOf(b1) == rows.indexOf(a1)) {
                System.out.println();
                System.out.println("That ticket has already been purchased!");
                System.out.println();
            } else if (a1 > a || b1 > b) {
                System.out.println();
                System.out.println("Wrong input!");
                System.out.println();
            } else {
                if (a * b <= 60) {
                    System.out.println("Ticket price: $10");
                    rows.add(a1);
                    seat_number.add(b1);

                    current_income += 10;

                } else if (a * b > 60) {
                    if (a < 10 && b < 10 && a1 < 10 && b1 < 10) {
                        if (a1 <= (int) (a / 2)) {
                            System.out.println("Ticket price: $10");

                            current_income += 10;

                        } else if (a1 > (int) (a / 2)) {
                            System.out.println("Ticket price: $8");

                            current_income += 8;

                        }

                        rows.add(a1);
                        seat_number.add(b1);
                    }
                }

                ++tickets_lots;
                percentage = ((float) tickets_lots * 100) / (a * b);

                System.out.println();
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // Write your code here

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the number of rows:");
        int a = Integer.parseInt(reader.readLine());
        System.out.println("Enter the number of seats in each row:");
        int b = Integer.parseInt(reader.readLine());
        System.out.println();

        set_total_income(a, b);

        while (true) {
            System.out.println(
                    "1. Show the seats\n" +
                            "2. Buy a ticket\n" +
                            "3. Statistics\n" +
                            "0. Exit"
            );
            int n = Integer.parseInt(reader.readLine());

            if (n == 1) {
                show_seats(a, b);
            } else if (n == 2) {
                buy_tickets(a, b);
            } else if (n == 3) {
                show_statistic();
            } else if (n == 0) {
                break;
            }
        }

    }
}