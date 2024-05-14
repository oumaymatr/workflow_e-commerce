package tn.enit.handler;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.api.worker.JobHandler;
import tn.enit.service.PasserCaisse;

public class PasserCaisseHandler implements JobHandler {
    PasserCaisse passerCaisseService = new PasserCaisse();

    @Override
    public void handle(JobClient client, ActivatedJob job) throws Exception {
        passerCaisseService.passerCaisse();
        //Pour dire au workflow engine que le client est passé à la caisse
        client.newCompleteCommand(job.getKey()).send().join();
    }
}
