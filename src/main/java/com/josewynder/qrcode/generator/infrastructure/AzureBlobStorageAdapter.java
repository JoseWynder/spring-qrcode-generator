package com.josewynder.qrcode.generator.infrastructure;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.sas.BlobSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
import com.azure.storage.common.StorageSharedKeyCredential;
import com.josewynder.qrcode.generator.ports.StoragePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class AzureBlobStorageAdapter implements StoragePort {

    private final BlobContainerClient containerClient;
    private final String accountName;
    private final String accountKey;
    private final String containerName;

    public AzureBlobStorageAdapter(
            @Value("${azure.storage.account-name}") String accountName,
            @Value("${azure.storage.account-key}") String accountKey,
            @Value("${azure.storage.container-name}") String containerName
    ) {
        this.accountName = accountName;
        this.accountKey = accountKey;
        this.containerName = containerName;

        StorageSharedKeyCredential credential = new StorageSharedKeyCredential(accountName, accountKey);

        BlobServiceClient serviceClient = new BlobServiceClientBuilder()
                .endpoint(String.format("https://%s.blob.core.windows.net", accountName))
                .credential(credential)
                .buildClient();

        this.containerClient = serviceClient.getBlobContainerClient(containerName);
        if (!this.containerClient.exists()) {
            this.containerClient.create();
        }
    }

    @Override
    public String uploadFile(byte[] fileData, String fileName, String contentType) {
        BlobClient blobClient = containerClient.getBlobClient(fileName);

        blobClient.upload(new java.io.ByteArrayInputStream(fileData), fileData.length, true);
        blobClient.setHttpHeaders(new BlobHttpHeaders().setContentType(contentType));

        BlobSasPermission permission = new BlobSasPermission().setReadPermission(true);
        OffsetDateTime expiryTime = OffsetDateTime.now().plusHours(1);

        BlobServiceSasSignatureValues sasValues = new BlobServiceSasSignatureValues(expiryTime, permission);
        String sasToken = blobClient.generateSas(sasValues);

        return blobClient.getBlobUrl() + "?" + sasToken;
    }
}
