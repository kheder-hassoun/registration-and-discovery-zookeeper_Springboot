package me.zookeeper.leader_election;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StatusController {

    private final LeaderElection leaderElection;

    @Autowired
    public StatusController(LeaderElection leaderElection) {
        this.leaderElection = leaderElection;
    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        String status = leaderElection.isLeader() ? "I am the leader" : "I am a follower";
        return ResponseEntity.ok(status);
    }
}
