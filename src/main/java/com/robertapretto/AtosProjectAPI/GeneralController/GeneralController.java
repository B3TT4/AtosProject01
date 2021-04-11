package com.robertapretto.AtosProjectAPI.GeneralController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.robertapretto.AtosProjectAPI.GeneralModels.CensusModel;
import com.robertapretto.AtosProjectAPI.GeneralModels.ElectionModel;
import com.robertapretto.AtosProjectAPI.GeneralModels.ProfessorModel;
import com.robertapretto.AtosProjectAPI.GeneralModels.StudentModel;
import com.robertapretto.AtosProjectAPI.GeneralModels.UserModel;
import com.robertapretto.AtosProjectAPI.GeneralRepositories.CensusRepository;
import com.robertapretto.AtosProjectAPI.GeneralRepositories.ElectionRepository;
import com.robertapretto.AtosProjectAPI.GeneralRepositories.ProfessorRepository;
import com.robertapretto.AtosProjectAPI.GeneralRepositories.StudentRepository;
import com.robertapretto.AtosProjectAPI.GeneralRepositories.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Swagger2RestController", description = "REST API")
@RestController
@RequestMapping("/general")
public class GeneralController {
	
	@Autowired	
	private ElectionRepository generalRepository;
	
	@Autowired
	private CensusRepository censusRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	//__________1__________\\
	
	@ApiOperation(value = "Retorna a data e hora atuais", response = Iterable.class, tags = "GetData")
	@GetMapping("/data")	
	public Object getData() {	
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	//__________2__________\\
	
	@ApiOperation(value = "Cadastra Temperatura em Farenheit e responde em Celsius", response = Iterable.class, tags = "FarenheitToCelsius")
	@GetMapping("/temp")
	public double getTemp(double temp){
		double celsius = (temp-32)*(0.5556);
		return celsius;
	}
	
	//__________3__________\\
	
	@ApiOperation(value = "Recebe um número e responde se e par ou impar", response = Iterable.class, tags = "TypeNumber")
	@GetMapping("/type")
	public String getType(int number){
		if(number % 2 == 0) {
			return ("Número Par: " + number);
		}
		else{
			return ("Número Ímpar: " + number);
		}
	
	}
	//__________4__________\\
	
	@ApiOperation(value = "Retorna todos os votos", response = Iterable.class, tags = "ToElect")
	@GetMapping("/election")
	public List<ElectionModel> getElection(){
		return generalRepository.findAll();
		
	}
	
	@ApiOperation(value = "Cadastra o voto", response = Iterable.class, tags = "ToElect")
	@PostMapping("/election")
	@ResponseStatus(HttpStatus.CREATED)
	public ElectionModel postElect(@RequestBody ElectionModel vote){
		return generalRepository.save(vote);
	}
	
	@ApiOperation(value = "Retorna todos o número de votos para um candidato", response = Iterable.class, tags = "ToElect")
	@GetMapping("/election/{candidate}")
	public String CountVotesbyCandidates(Integer candidate) {
		List<ElectionModel> votes = generalRepository.findAll();
		ElectionModel x = null;
		int votation = 0;
		for (int i = 0; i <votes.size(); i++) {
			x = votes.get(i);
			if(x.code.equals(candidate)) {
				votation = votation + 1;
			}
			
		}
		return ("O Número de Votos para o candidato " +candidate+ " é: " +votation);
		
	}
	
	@ApiOperation(value = "Retorna todos o resultado da eleição", response = Iterable.class, tags = "ToElect")
	@GetMapping("/election/results")
	public String CountVotes() {
		List<ElectionModel> votes = generalRepository.findAll();
		ElectionModel x = null;
		int votation = 0;
		int reference = votes.get(0).code;
		for (int i = 0; i <votes.size(); i++) {
			x = votes.get(i);
			if(x.code.equals(reference)) {
				votation = votation + 1;
			}
			
		}
		return ("O Número de Votos para o candidato 1 é: " +votation+ "\n O número de Votos para o candidato 2 é: "+(votes.size()-votation));
		
	}
	
	
	//__________5__________\\
	
	@ApiOperation(value = "Retorna o Censo", response = Iterable.class, tags = "GetCensus")
	@GetMapping("/census")
	public List<CensusModel> getCensus(){
		return censusRepository.findAll();
		
	}
	
	@ApiOperation(value = "Cadastra o Censo", response = Iterable.class, tags = "GetCensus")
	@PostMapping("/census")
	@ResponseStatus(HttpStatus.CREATED)
	public CensusModel postCensus(@RequestBody CensusModel census){
		return censusRepository.save(census);
	}
	
	@ApiOperation(value = "Altera o censo por id", response = Iterable.class, tags = "GetCensus")
	@PutMapping("/census/{id}")
	public CensusModel updatePessoa(@RequestBody CensusModel census, @PathVariable Long id) {
		CensusModel cen = censusRepository.getOne(id);
		if(cen == null) {
			return null;
		}
		cen.setPeoples(census.getPeoples());
		return censusRepository.save(cen);
	}
	
	@ApiOperation(value = "Deleta censo pelo id", response = Iterable.class, tags = "GetCensus")
	@DeleteMapping("/census/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePessoa(@PathVariable Long id) {
		censusRepository.deleteById(id);
	}
	
	@ApiOperation(value = "Procura censo pelo ano", response = Iterable.class, tags = "GetCensus")
	@GetMapping("/census/{year}")
	public CensusModel getCensusByYear(@PathVariable Integer year) {
		CensusModel x = null;
		CensusModel retorno = null;
		List<CensusModel> census = censusRepository.findAll();
		for (int i = 0; i <census.size(); i++) {
			x = census.get(i);
			if(x.year.equals(year)) {
				retorno = x;
			}
		}
		return retorno;
	}
	
	//__________6__________\\
	
	@ApiOperation(value = "Cadastra o Professor", response = Iterable.class, tags = "UserContent")
	@PostMapping("/professor")
	@ResponseStatus(HttpStatus.CREATED)
	public ProfessorModel postProfessor(@RequestBody ProfessorModel professor){
		return professorRepository.save(professor);
	}
	
	@ApiOperation(value = "Cadastra o Estudante", response = Iterable.class, tags = "UserContent")
	@PostMapping("/student")
	@ResponseStatus(HttpStatus.CREATED)
	public StudentModel postStudent(@RequestBody StudentModel student){
		return studentRepository.save(student);
	}
	
	//NÃO CONSEGUI
	@ApiOperation(value = "Retorna todos os cadastrados", response = Iterable.class, tags = "UserContent")
	@GetMapping("/users")
	public String getUsers(){
		List<StudentModel> toReturnStudent = studentRepository.findAll();
		List<ProfessorModel> toReturnProfessor = professorRepository.findAll();
		
		return ("Lista de usuários: " + toReturnStudent + toReturnProfessor);
		
	}
	
	@ApiOperation(value = "Retorna todos os alunos", response = Iterable.class, tags = "UserContent")
	@GetMapping("/students")
	public List<StudentModel> getStudents(){
		List<StudentModel> toReturnStudent = studentRepository.findAll();
		return toReturnStudent;
	}
	
	@ApiOperation(value = "Retorna todos os Professores", response = Iterable.class, tags = "UserContent")
	@GetMapping("/professor")
	public List<ProfessorModel>  getProfessor(){
		List<ProfessorModel> toReturnProfessor = professorRepository.findAll();
		return toReturnProfessor;
	}
	
	@ApiOperation(value = "Altera o aluno por id", response = Iterable.class, tags = "UserContent")
	@PutMapping("/student/{id}")
	public StudentModel updateStudent(@RequestBody StudentModel student, @PathVariable Long id) {
		StudentModel stu = studentRepository.getOne(id);
		if(stu == null) {
			return null;
		}
		stu.setName(student.getName());
		return studentRepository.save(stu);
	}
	
	@ApiOperation(value = "Deleta aluno pelo id", response = Iterable.class, tags = "UserContent")
	@DeleteMapping("/student/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteStudent(@PathVariable Long id) {
		studentRepository.deleteById(id);
	}
	
	@ApiOperation(value = "Altera o professor por id", response = Iterable.class, tags = "UserContent")
	@PutMapping("/professor/{id}")
	public ProfessorModel updateProfessor(@RequestBody ProfessorModel professor, @PathVariable Long id) {
		ProfessorModel pro = professorRepository.getOne(id);
		if(pro == null) {
			return null;
		}
		pro.setName(professor.getName());
		return professorRepository.save(pro);
	}
	
	@ApiOperation(value = "Deleta professor pelo id", response = Iterable.class, tags = "UserContent")
	@DeleteMapping("/professor/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProfessor(@PathVariable Long id) {
		professorRepository.deleteById(id);
	}

}
