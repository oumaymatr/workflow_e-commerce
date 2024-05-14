package tn.enit.handler;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.api.worker.JobHandler;
import tn.enit.service.AjouterProduit;

public class AjouterProduitHandler implements JobHandler {

    AjouterProduit ajouterProduitService = new AjouterProduit();

    @Override
    public void handle(JobClient client, ActivatedJob job) throws Exception {
        ajouterProduitService.ajouterProduit();
        //Pour dire au workflow engine que le produit a été ajouré au panier
        client.newCompleteCommand(job.getKey()).send().join();
    }
}