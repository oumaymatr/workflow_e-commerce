package tn.enit.handler;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.api.worker.JobHandler;
import tn.enit.service.ChercherProduit;

public class ChercherProduitHandler implements JobHandler {

    ChercherProduit chercherProduitService = new ChercherProduit();

    @Override
    public void handle(JobClient client, ActivatedJob job) throws Exception {
        chercherProduitService.chercherProduit();
        //Pour dire au workflow engine que le produit a été cherché
        client.newCompleteCommand(job.getKey()).send().join();
    }
}