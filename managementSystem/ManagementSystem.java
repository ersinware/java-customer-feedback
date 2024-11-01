import java.util.Scanner;

import actions.Actions;
import constants.Constants;

public class ManagementSystem {
    private static boolean attendedToSurvey = false;

    public static void start() {
        if (!makeSignedIn())
            return;

        System.out.println("\nSIGNED INTO THE SYSTEM");

        if (Actions.isAuthenticatedUserAdmin())
            onAdminSignedIn();
        else
            onUserSignedIn();
    }

    private static boolean makeSignedIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("WELCOME TO CUSTOMER MANAGEMENT SYSTEM");

        while (true) {
            System.out.println("\nTo sign in, enter your username");
            System.out.println("To sign up, press 1");
            System.out.println("To close, type EXIT\n");

            String input = scanner.nextLine();
            if (input.equals("EXIT"))
                return false;

            if (input.equals("1")) {
                if (signUp(scanner)) {
                    System.out.println("\nSigned up successfuly");

                    return true;
                }

                continue;
            }

            System.out.println("\nTo sign in, enter your password\n");
            String password = scanner.nextLine();
            if (!Actions.signIn(input, password))
                continue;

            return true;
        }
    }

    private static boolean signUp(Scanner scanner) {
        System.out.println("\nEnter your username\n");
        String username = scanner.nextLine();

        System.out.println("\nEnter your password\n");
        String password = scanner.nextLine();

        System.out.println("\nEnter your name\n");
        String name = scanner.nextLine();

        System.out.println("\nEnter your surname\n");
        String surname = scanner.nextLine();

        return Actions.signUp(username, password, name, surname);
    }

    private static void onAdminSignedIn() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n(1) list all feedbacks");
        System.out.println("(2) list all categories");
        System.out.println("To close, type EXIT\n");

        String input = scanner.nextLine();
        switch (input) {
            case "EXIT":
                scanner.close();
                return;
            case "1":
                onFeedbacks(scanner);
                break;
            case "2":
                onCategories(scanner);
                break;
            default:
                System.out.println("\nError");
                onAdminSignedIn();
        }
    }

    private static void onFeedbacks(Scanner scanner) {
        System.out.println("\n(#index) view feedback details");
        System.out.println("(BACK)\n");

        String[] feedbackTitles = Actions.getAllFeedbackTitles();
        for (int i = 0; i < feedbackTitles.length; i++)
            System.out.println(i + "#" + feedbackTitles[i]);

        System.out.println("");

        String input = scanner.nextLine();
        if (input.equals("BACK")) {
            onAdminSignedIn();

            return;
        }

        try {
            onFeedbackDetail(scanner, feedbackTitles[Integer.parseInt(input)]);
        } catch (Exception e) {
            System.out.println("\nError");
            onFeedbacks(scanner);
        }
    }

    private static void onFeedbackDetail(Scanner scanner, String title) {
        System.out.println("\n(1) response feedback");
        System.out.println("(2) response feedback with template");
        System.out.println("(BACK)");

        System.out.println("\n" + Actions.getFeedbackDetails(title) + "\n");

        String input = scanner.nextLine();
        switch (input) {
            case "BACK":
                onFeedbacks(scanner);
                break;
            case "1":
                System.out.println("\nEnter your response\n");
                String response = scanner.nextLine();
                if (Actions.responseFeedback(title, response))
                    System.out.println("\nThe response shared successfuly");

                onFeedbacks(scanner);
                break;
            case "2":
                if (Actions.responseFeedbackWithTemplate(title, getResponseWithTemplate(scanner)))
                    System.out.println("\nThe response shared successfuly");

                onFeedbacks(scanner);
                break;
            default:
                System.out.println("\nError");
                onFeedbackDetail(scanner, title);
                break;
        }
    }

    private static String getResponseWithTemplate(Scanner scanner) {
        System.out.println("\nChoose your response\n");
        System.out.println("(0) " + Constants.FEEDBACK_RESPONSE_TEMPLATE_ONE);
        System.out.println("(1) " + Constants.FEEDBACK_RESPONSE_TEMPLATE_TWO);
        System.out.println("(2) " + Constants.FEEDBACK_RESPONSE_TEMPLATE_THREE + "\n");

        String index = scanner.nextLine();
        String selectedResponse;
        switch (index) {
            case "0":
                selectedResponse = Constants.FEEDBACK_RESPONSE_TEMPLATE_ONE;
                break;
            case "1":
                selectedResponse = Constants.FEEDBACK_RESPONSE_TEMPLATE_TWO;
                break;
            case "2":
                selectedResponse = Constants.FEEDBACK_RESPONSE_TEMPLATE_THREE;
                break;
            default:
                System.out.println("\nError");
                return getResponseWithTemplate(scanner);
        }

        return selectedResponse;
    }

    private static void onCategories(Scanner scanner) {
        System.out.println("\n(#ADD [categoryName])");
        System.out.println("(#index DELETE)");
        System.out.println("(#index EDIT)");
        System.out.println("(BACK)\n");

        String[] categories = Actions.getAllCategoryNames();
        for (int i = 0; i < categories.length; i++)
            System.out.println(i + "#" + categories[i]);

        System.out.println("");

        onCategoriesOptions(scanner, categories);
    }

    private static void onCategoriesOptions(Scanner scanner, String[] categories) {
        String input = scanner.nextLine();

        if (input.equals("BACK")) {
            onAdminSignedIn();

            return;
        }

        if (input.contains("ADD")) {
            String[] commandAndCategoryName = input.split(" ");

            if (commandAndCategoryName.length < 2) {
                System.out.println("\nError");

                onCategories(scanner);

                return;
            }

            String categoryName = "";
            for (int i = 1; i < commandAndCategoryName.length; i++)
                categoryName += commandAndCategoryName[i] + " ";

            if (Actions.addCategory(categoryName.trim()))
                System.out.println("\nThe category added successfuly");

            onCategories(scanner);

            return;

        }

        if (input.contains("DELETE")) {
            String[] indexAndCommand = input.split(" ");

            if (indexAndCommand.length != 2) {
                System.out.println("\nError");

                onCategories(scanner);

                return;
            }

            try {
                int index = Integer.parseInt(indexAndCommand[0]);

                if (Actions.removeCategory(categories[index]))
                    System.out.println("\nThe category removed successfuly");

                onCategories(scanner);

                return;
            } catch (Exception e) {
                System.out.println("\nError");
                onCategories(scanner);

                return;
            }
        }

        if (input.contains("EDIT")) {
            String[] indexAndCommand = input.split(" ");

            if (indexAndCommand.length != 2) {
                System.out.println("\nError");

                onCategories(scanner);

                return;
            }

            try {
                int index = Integer.parseInt(indexAndCommand[0]);

                System.out.println("\nEnter new category name\n");
                String newCategoryName = scanner.nextLine();

                if (Actions.editCategory(categories[index], newCategoryName))
                    System.out.println("\nThe category edited successfully");

                onCategories(scanner);

                return;
            } catch (Exception e) {
                System.out.println("\nError");
                onCategories(scanner);

                return;
            }
        }

        onCategories(scanner);
    }

    private static void onUserSignedIn() {
        // BECAUSE OF BUG IN Scanner
        Scanner scanner = new Scanner(System.in);

        if (!attendedToSurvey) {
            System.out.println("\n(1) attend to survey");
            System.out.println("(2) list all feedbacks");
            System.out.println("(3) share feedback");
            System.out.println("To close, type EXIT\n");

            String input = scanner.nextLine();
            switch (input) {
                case "EXIT":
                    scanner.close();
                    return;
                case "1":
                    onSurvey(scanner);
                    break;
                case "2":
                    onUserFeedbacks(scanner);
                    break;
                case "3":
                    shareFeedback(scanner);
                    break;
                default:
                    System.out.println("\nError");
                    onUserSignedIn();
            }

            return;
        }

        System.out.println("\n(1) list all feedbacks");
        System.out.println("(2) share feedback");
        System.out.println("To close, type EXIT\n");

        String input = scanner.nextLine();
        switch (input) {
            case "EXIT":
                scanner.close();
                return;
            case "1":
                onUserFeedbacks(scanner);
                break;
            case "2":
                shareFeedback(scanner);
                break;
            default:
                System.out.println("\nError");
                onUserSignedIn();
        }
    }

    private static void onSurvey(Scanner scanner) {
        System.out.println("\nSurvey question one?\n");
        scanner.nextLine();

        System.out.println("\nSurvey question two?\n");
        scanner.nextLine();

        System.out.println("\nSurvey question three?\n");
        scanner.nextLine();

        attendedToSurvey = true;
        onUserSignedIn();
    }

    private static void onUserFeedbacks(Scanner scanner) {
        System.out.println("\n(#index) view feedback details");
        System.out.println("(BACK)\n");

        String[] feedbackTitles = Actions.getAllFeedbackTitlesOfAuthenticatedUser();
        for (int i = 0; i < feedbackTitles.length; i++)
            System.out.println(i + "#" + feedbackTitles[i]);

        System.out.println("");

        String input = scanner.nextLine();
        if (input.equals("BACK")) {
            onUserSignedIn();

            return;
        }

        try {
            onUserFeedbackDetail(scanner, feedbackTitles[Integer.parseInt(input)]);
        } catch (Exception e) {
            System.out.println("\nError");
            onUserFeedbacks(scanner);
        }
    }

    private static void onUserFeedbackDetail(Scanner scanner, String title) {
        System.out.println("\n(1) response feedback");
        System.out.println("(BACK)");

        System.out.println("\n" + Actions.getFeedbackDetails(title) + "\n");

        String input = scanner.nextLine();
        switch (input) {
            case "BACK":
                onUserFeedbacks(scanner);
                break;
            case "1":
                System.out.println("\nEnter your response\n");
                String response = scanner.nextLine();
                if (Actions.responseFeedback(title, response))
                    System.out.println("\nThe response shared successfuly");

                onUserFeedbacks(scanner);
                break;
            default:
                System.out.println("\nError");
                onUserFeedbackDetail(scanner, title);
                break;
        }
    }

    private static void shareFeedback(Scanner scanner) {
        System.out.println("\n(#index) choose category\n");

        String[] categories = Actions.getAllCategoryNames();
        for (int i = 0; i < categories.length; i++)
            System.out.println(i + "#" + categories[i]);

        System.out.println("");

        String input = scanner.nextLine();
        try {
            int index = Integer.parseInt(input);
            String categoryName = categories[index];

            System.out.println("\nEnter title\n");
            String title = scanner.nextLine();

            System.out.println("\nEnter your thoughts\n");
            String thoughts = scanner.nextLine();

            System.out.println("\nEnter rate\n");
            int rate = scanner.nextInt();

            if (Actions.shareFeedback(title, thoughts, categoryName, rate))
                System.out.println("\nFeedback shared successfuly");

            onUserSignedIn();
        } catch (Exception e) {
            System.out.println("\nError");
            onUserSignedIn();
        }
    }
}