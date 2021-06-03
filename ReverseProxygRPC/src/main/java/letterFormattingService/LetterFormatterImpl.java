package letterFormattingService;

import io.grpc.stub.StreamObserver;
import letterFormatting.LetterFormatRequest;
import letterFormatting.LetterFormatResponse;
import letterFormatting.LetterFormatterGrpc;

public class LetterFormatterImpl extends LetterFormatterGrpc.LetterFormatterImplBase {

    @Override
    public void formatLetters(LetterFormatRequest request, StreamObserver<LetterFormatResponse> observer) {
        System.out.println("Received request to format text.");
        String formatted = request.getToUpper() ? request.getText().toUpperCase() : request.getText().toLowerCase();
        observer.onNext(LetterFormatResponse.newBuilder().setText(formatted).build());
        observer.onCompleted();
        System.out.println("Request resolved successfully");
    }
}
