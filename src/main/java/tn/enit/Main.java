package tn.enit;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.worker.JobWorker;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProvider;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProviderBuilder;
import tn.enit.handler.*;

import java.time.Duration;

public class Main {
    private static final String ZEEBE_ADDRESS = "348a1e99-8e4e-403c-a86c-8cc8c677a539.lhr-1.zeebe.camunda.io:443";
    private static final String ZEEBE_CLIENT_ID = "sLk8rMFs7OHAPFpVAAZvpvflqoY_smBU";
    private static final String ZEEBE_CLIENT_SECRET = "VLKWIsFbdUE5qPDQK1pJlx3pYb4pCpPs2c.TIi2jfTkDZulMdl5o8I9PYwpFgtEU";
    private static final String ZEEBE_AUTHORIZATION_SERVER_URL = "https://login.cloud.camunda.io/oauth/token";
    private static final String ZEEBE_TOKEN_AUDIENCE = "zeebe.camunda.io";

    public static void main(String[] args) {
        final OAuthCredentialsProvider credentialsProvider =
                new OAuthCredentialsProviderBuilder()
                        .authorizationServerUrl(ZEEBE_AUTHORIZATION_SERVER_URL)
                        .audience(ZEEBE_TOKEN_AUDIENCE)
                        .clientId(ZEEBE_CLIENT_ID)
                        .clientSecret(ZEEBE_CLIENT_SECRET)
                        .build();

        try (final ZeebeClient client =
                     ZeebeClient.newClientBuilder()
                             .gatewayAddress(ZEEBE_ADDRESS)
                             .credentialsProvider(credentialsProvider)
                             .build()) {
            final JobWorker chercherProduitWorker =
                    client.newWorker()
                            .jobType("chercherProduit")
                            .handler(new ChercherProduitHandler())
                            .timeout(Duration.ofSeconds(10).toMillis())
                            .open();
            Thread.sleep(10000);
            final JobWorker ajouterProduitWorker =
                    client.newWorker()
                            .jobType("ajouterProduit")
                            .handler(new AjouterProduitHandler())
                            .timeout(Duration.ofSeconds(10).toMillis())
                            .open();
            Thread.sleep(10000);
            final JobWorker gererPanierWorker =
                    client.newWorker()
                            .jobType("gererPanier")
                            .handler(new GererPanierHandler())
                            .timeout(Duration.ofSeconds(10).toMillis())
                            .open();
            Thread.sleep(10000);
            final JobWorker passerCaisseWorker =
                    client.newWorker()
                            .jobType("passerCaisse")
                            .handler(new PasserCaisseHandler())
                            .timeout(Duration.ofSeconds(10).toMillis())
                            .open();
            Thread.sleep(10000);
            final JobWorker connecterWorker =
                    client.newWorker()
                            .jobType("connecter")
                            .handler(new ConnecterHandler())
                            .timeout(Duration.ofSeconds(10).toMillis())
                            .open();
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}