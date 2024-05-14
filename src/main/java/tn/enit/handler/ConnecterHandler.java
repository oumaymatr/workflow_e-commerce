package tn.enit.handler;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.api.worker.JobHandler;
import tn.enit.service.Connecter;

public class ConnecterHandler implements JobHandler {
    Connecter connecterService = new Connecter();

    @Override
    public void handle(JobClient client, ActivatedJob job) throws Exception {
        connecterService.connecter();
        //Pour dire au workflow engine que le client est connecté
        client.newCompleteCommand(job.getKey()).send().join();
    }
}
