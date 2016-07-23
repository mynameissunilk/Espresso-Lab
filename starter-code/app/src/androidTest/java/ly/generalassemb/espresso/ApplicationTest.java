package ly.generalassemb.espresso;

import android.app.Application;
import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
/**
 *** User Stories***
 1: can view current balance
 2: can withdraw $ from acct
 3: can deposit $ into acct
 4: perform back-to-back withdrawals & deposits
**/

@RunWith(AndroidJUnit4.class)
public class ApplicationTest extends ApplicationTestCase<Application> {

    //constructor @#$%
    public ApplicationTest() {
        super(Application.class);
    }

    @Rule
    public ActivityTestRule<BalanceActivity>mBalanceRule = new ActivityTestRule<BalanceActivity>(BalanceActivity.class);

//    @Rule
//    public ActivityTestRule<NewTransactionActivity>mTransactionRule = new ActivityTestRule<>(NewTransactionActivity.class);

        String description = "Boeing 777";
        String price = "261000000";
        String depexpectedValue="$261,000,000.00";
        String withexpectedValue = "-$261,000,000.00";

    @Test
    public void withdrawTest() throws Exception{

        // first activity
        onView(withId(R.id.newTransactionButton)).perform(click());

        onView(withId(R.id.descriptionEditText))
                .perform(typeText(description),closeSoftKeyboard());

        onView(withId(R.id.amountEditText))
                .perform(typeText(price),closeSoftKeyboard());

        onView(withId(R.id.withdrawButton)).perform(click());

        onView(withId(R.id.balanceTextView)).check(matches(withText(withexpectedValue)));

    }
    // can deposit money
    @Test
    public void depositTest() throws Exception{

        onView(withId(R.id.newTransactionButton)).perform(click());

        onView(withId(R.id.descriptionEditText))
                .perform(typeText(description),closeSoftKeyboard());

        onView(withId(R.id.amountEditText))
                .perform(typeText(price),closeSoftKeyboard());

        onView(withId(R.id.depositButton)).perform(click());

        onView(withId(R.id.balanceTextView)).check(matches(withText(depexpectedValue)));

    }
    @Test
    public void balanceTest() throws Exception{

        onView(withId(R.id.balanceTextView)).check(matches(isDisplayed()));

    }

    @Test
    public void multipleTransactionTest() throws Exception{
        onView(withId(R.id.newTransactionButton)).perform(click());
        onView(withId(R.id.descriptionEditText)).perform(typeText("tacos"));
        onView(withId(R.id.amountEditText)).perform(typeText("20"));
        onView(withId(R.id.depositButton)).perform(click());
        onView(withId(R.id.balanceTextView)).check(matches(withText("$20.00")));

        onView(withId(R.id.newTransactionButton)).perform(click());
        onView(withId(R.id.descriptionEditText)).perform(typeText("taco subsidy"));
        onView(withId(R.id.amountEditText)).perform(typeText("10"));
        onView(withId(R.id.withdrawButton)).perform(click());
        onView(withId(R.id.balanceTextView)).check(matches(withText("$10.00")));

        onView(withId(R.id.newTransactionButton)).perform(click());
        onView(withId(R.id.descriptionEditText)).perform(typeText("Greek Bailout"));
        onView(withId(R.id.amountEditText)).perform(typeText("1000000000"));
        onView(withId(R.id.withdrawButton)).perform(click());
        onView(withId(R.id.balanceTextView)).check(matches(withText("-$999,999,990.00")));
    }
}