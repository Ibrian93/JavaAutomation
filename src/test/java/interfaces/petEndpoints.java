package interfaces;

public interface petEndpoints {
    String petEndpoint = "/pet";
    String findByStatusEndpoint = "/pet/findByStatus";
    String uploadImageEndpoint = "/pet/%d/uploadImage";
}
