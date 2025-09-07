package checkout;

import org.testng.annotations.*;

public class ShippingTests {

    @BeforeClass
    public void setup() {}

    @Test(priority = 1)
    public void testDisplayShippingMethods() {}

    @Test(priority = 2)
    public void testStandardShippingCost() {}

    @Test(priority = 3)
    public void testExpressShippingCost() {}

    @Test(priority = 4)
    public void testShippingOptionPersistence() {}

    @AfterClass
    public void tearDown() {}
}
