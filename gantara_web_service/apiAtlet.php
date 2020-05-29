<?php
	include 'atlet.php';
	include 'config.php';

	function isTheseParametersAvailable($params){
		$available = true;
		$missingparams = "";
		foreach($params as $param){
			if(!isset($_POST[$param]) || strlen($_POST[$param])<=0){
				$available = false;
				$missingparams = $missingparams . ", " . $param;
			}
		}
		if(!$available){
			$response = array();
			$response['error'] = true;
			$response['message'] = 'Parameters' . substr($missingparams, 1,
			strlen($missingparams)) . ' missing';
			echo json_encode($response);
			die();
		}
	}

	$response = array();
	if(isset($_GET['apicall'])){
		switch($_GET['apicall']){
			case 'signup': //update
				isTheseParametersAvailable(array('idPsikolog', 'nama', 'cabor', 'gander', 'tempatLahir', 'tanggalLahir', 'alamat', 'kotaAsal', 'noTelfon', 'userName', 'pass', 'photo'));
				$result=signup($conn, $_POST['idPsikolog'], $_POST['nama'], $_POST['cabor'], $_POST['gander'], $_POST['tempatLahir'], $_POST['tanggalLahir'], $_POST['alamat'], $_POST['kotaAsal'], $_POST['noTelfon'], $_POST['userName'], $_POST['pass'], $_POST['photo']);
				if($result){
					$response = array("status" => 1, "massage" => "Success!", "data" => $result);
				}else{
					$response = array("status" => 0, "massage" => "Some error!", "data" => array());
				}
				break;
			case 'login': //get
				isTheseParametersAvailable(array('userName', 'pass', 'token'));
				$result=login($conn, $_POST['userName'], $_POST['pass'], $_POST['token']);
				if($result){
					$response = array("status" => 1, "massage" => "Success!", "data" => $result);
				}else{
					$response = array("status" => 0, "massage" => "Some error!", "data" => array());
				}
				break;
			case 'info': //get
				$response = array("status" => 1, "massage" => "Success!", "data" => info($conn));
				break;
			case 'addForm': //insert
				isTheseParametersAvailable(array('idAtlet', 'idPsikolog', 'sesiLatihan', 'antusiasmePreLatihan', 'antusiasmePosLatihan', 'fisik', 'catatanFisik', 'stres', 'konsentrasi', 'keyakinan', 'target', 'lelah', 'komunikasi', 'intensitas', 'hidrasi', 'tidur', 'nutrisi', 'recovery', 'recoveryLain', 'mentalSkill', 'mentalSkillLain', 'kendalaMentalSkill', 'saranMasalah', 'saranMasalahLain', 'halPositif', 'scoringMental', 'scoringFisik', 'intensitasTarget'));
    			$status = 1;
				$result=addForm($conn, $_POST['idAtlet'], $_POST['idPsikolog'], $_POST['sesiLatihan'], $_POST['antusiasmePreLatihan'], $_POST['antusiasmePosLatihan'], $_POST['fisik'], $_POST['catatanFisik'], $_POST['stres'], $_POST['konsentrasi'], $_POST['keyakinan'], $_POST['target'], $_POST['lelah'], $_POST['komunikasi'], $_POST['intensitas'], $_POST['hidrasi'], $_POST['tidur'], $_POST['nutrisi'], $_POST['recovery'], $_POST['recoveryLain'], $_POST['mentalSkill'], $_POST['mentalSkillLain'], $_POST['kendalaMentalSkill'], $_POST['saranMasalah'], $_POST['saranMasalahLain'], $_POST['halPositif'], $_POST['scoringMental'], $_POST['scoringFisik'], $_POST['intensitasTarget'], $status);
				if($result){
					$response = array("status" => 1, "massage" => "Success!", "data" => $result);
				}else{
					$response = array("status" => 0, "massage" => "Some error!", "data" => array());
				}
				break;
			case 'tanggapan': //get ListTanggapan
				isTheseParametersAvailable(array('idAtlet'));
				$statusRead = 1;
				$result=tanggapan($conn, $_POST['idAtlet'], $statusRead);
				if($result){
					$response = array("status" => 1, "massage" => "Success!", "data" => $result);
				}else{
					$response = array("status" => 0, "massage" => "Some error!", "data" => array());
				}
				break;
			case 'readStatusRekamMedis': //update
				isTheseParametersAvailable(array('idForm'));
				$statusRead = 2;
				$result=readStatusRekamMedis($conn, $_POST['idForm'], $statusRead);
				if($result){
					$response = array("status" => 1, "massage" => "Success!", "data" => $result);
				}else{
					$response = array("status" => 0, "massage" => "Some error!", "data" => array());
				}
				break;
			case 'rekamMedis': //get ListRekamMedis
				isTheseParametersAvailable(array('idAtlet'));
				$statusRead = 2;
				$result=tanggapan($conn, $_POST['idAtlet'], $statusRead);
				if($result){
					$response = array("status" => 1, "massage" => "Success!", "data" => $result);
				}else{
					$response = array("status" => 0, "massage" => "Some error!", "data" => array());
				}
				break;
			case 'statistik': //get 
				isTheseParametersAvailable(array('idAtlet'));
				$result=statistik($conn, $_POST['idAtlet']);
				if($result){
					$response = array("status" => 1, "massage" => "Success!", "data" => $result);
				}else{
					$response = array("status" => 0, "massage" => "Some error!", "data" => array());
				}
				break;
			case 'profile': //get
				isTheseParametersAvailable(array('idAtlet', 'idPsikolog', 'userName', 'pass', 'passKonf', 'alamat', 'kotaAsal', 'noTelfon', 'cabor'));
				$result=profile($conn, $_POST['idAtlet'], $_POST['idPsikolog'], $_POST['userName'], $_POST['pass'], $_POST['passKonf'], $_POST['alamat'], $_POST['kotaAsal'], $_POST['noTelfon'], $_POST['cabor']);
				if($result){
					$response = array("status" => 1, "massage" => "Success!", "data" => $result);
				}else{
					$response = array("status" => 0, "massage" => "Some error!", "data" => array());
				}
				break;
			case 'photoProfile': //update
				isTheseParametersAvailable(array('userName', 'photoProfile'));
				$result=photoProfile($conn, $_POST['userName'], $_POST['photoProfile']);
				if($result){
					$response = array("status" => 1, "massage" => "Success!", "data" => $result);
				}else{
					$response = array("status" => 0, "massage" => "Some error!", "data" => array());
				}
				break;
			case 'sharePref': //get
				isTheseParametersAvailable(array('userName'));
				$result=sharePref($conn, $_POST['userName']);
				if($result){
					$response = array("status" => 1, "massage" => "Success!", "data" => $result);
				}else{
					$response = array("status" => 0, "massage" => "Some error!", "data" => array());
				}
				break;
		}
	}else{
		$response['error'] = true;
		$response['message'] = 'Invalid API Call';
	}
	echo json_encode($response);
?>