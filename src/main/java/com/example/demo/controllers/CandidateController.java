package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Candidate;

@RestController
@RequestMapping("/api2")
public class CandidateController {

	// Global variable to store candidates
	private List<Candidate> candidates = Collections.synchronizedList(new ArrayList<>());

	// API to add a candidate
	@PostMapping("/add")
	public String addCandidate(@RequestBody Candidate candidate) {
		if (!candidates.isEmpty()) {
			int lastId = candidates.get(candidates.size() - 1).getId(); // get last candidate ID
			candidate.setId(lastId + 1); // increment by 1
		} else {
			candidate.setId(1); // first candidate
		}

		candidates.add(candidate);
		return "Candidate added successfully!";
	}

	// API to fetch all candidates
	@GetMapping("/get")
	public List<Candidate> getCandidates() {
		return candidates;
	}

	// Optional: Clear all candidates
	@DeleteMapping("/clear")
	public String clearCandidates() {
		candidates.clear();
		return "All candidates cleared!";
	}

	// API to update a candidate by ID
	@PutMapping("/update/{id}")
	public String updateCandidate(@PathVariable int id, @RequestBody Candidate updatedCandidate) {
		for (Candidate candidate : candidates) {
			if (candidate.getId() == id) {
				candidate.setName(updatedCandidate.getName());
				candidate.setEmail(updatedCandidate.getEmail());
				return "Candidate updated successfully!";
			}
		}
		return "Candidate with ID " + id + " not found!";
	}
}
