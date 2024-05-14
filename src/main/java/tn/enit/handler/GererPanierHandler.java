package tn.enit.handler;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.api.worker.JobHandler;
import tn.enit.service.GererPanier;

public class GererPanierHandler implements JobHandler{
    GererPanier gererPanierService = new GererPanier();

    @Override
    public void handle(JobClient client, ActivatedJob job) throws Exception {
        gererPanierService.gererPanier();
        //Pour dire au workflow engine que le panier a été géré
        client.newCompleteCommand(job.getKey()).send().join();
    }
}
