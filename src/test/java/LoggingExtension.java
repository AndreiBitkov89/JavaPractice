import org.junit.jupiter.api.extension.*;

public class LoggingExtension implements  TestWatcher {

    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.println("✅Test PASSED: " + context.getDisplayName());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("❌Test FAILED: " + context.getDisplayName());
        System.out.println("Reason: " + cause.getMessage());
    }

}
