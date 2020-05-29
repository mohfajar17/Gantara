<?php
	include 'psikolog.php';
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
				isTheseParametersAvailable(array('nama', 'gander', 'tempatLahir', 'tanggalLahir', 'alamat', 'noTelfon', 'userName', 'pass', 'photo'));
				$result=signup($conn, $_POST['nama'], $_POST['gander'], $_POST['tempatLahir'], $_POST['tanggalLahir'], $_POST['alamat'], $_POST['noTelfon'], $_POST['userName'], $_POST['pass'], $_POST['photo']);
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
			case 'addInfo': //insert
				isTheseParametersAvailable(array('idPsikolog', 'judul', 'dari', 'untuk', 'isiInfo'));
				$result=addInfo($conn, $_POST['idPsikolog'], $_POST['judul'], $_POST['dari'], $_POST['untuk'], $_POST['isiInfo']);
				if($result){
					$response = array("status" => 1, "massage" => "Success!", "data" => $result);
				}else{
					$response = array("status" => 0, "massage" => "Some error!", "data" => array());
				}
				break;
			case 'form': //get
				isTheseParametersAvailable(array('idPsikolog'));
				$result=form($conn, $_POST['idPsikolog']);
				if($result){
					$response = array("status" => 1, "massage" => "Success!", "data" => $result);
				}else{
					$response = array("status" => 0, "massage" => "Some error!", "data" => array());
				}
				break;
			case 'addTanggapan': //update
				isTheseParametersAvailable(array('idForm', 'catatanPsikolog'));
				$result=addTanggapan($conn, $_POST['idForm'], $_POST['catatanPsikolog']);
				if($result){
					$response = array("status" => 1, "massage" => "Success!", "data" => $result);
				}else{
					$response = array("status" => 0, "massage" => "Some error!", "data" => array());
				}
				break;
			case 'readForm': //get
				isTheseParametersAvailable(array('idForm'));
				$result=readForm($conn, $_POST['idForm']);
				if($result){
					$response = array("status" => 1, "massage" => "Success!", "data" => $result);
				}else{
					$response = array("status" => 0, "massage" => "Some error!", "data" => array());
				}
				break;
			case 'daftarAtlet': //get
				isTheseParametersAvailable(array('idPsikolog'));
				$result=daftarAtlet($conn, $_POST['idPsikolog']);
				if($result){
					$response = array("status" => 1, "massage" => "Success!", "data" => $result);
				}else{
					$response = array("status" => 0, "massage" => "Some error!", "data" => array());
				}
				break;
			case 'addAtlet': //get
				isTheseParametersAvailable(array('idPsikolog'));
				$result=addAtlet($conn, $_POST['idPsikolog']);
				if($result){
					$response = array("status" => 1, "massage" => "Success!", "data" => $result);
				}else{
					$response = array("status" => 0, "massage" => "Some error!", "data" => array());
				}
				break;
			case 'checkAddAtlet': //get
				isTheseParametersAvailable(array('idAtlet'));
				$result=checkAddAtlet($conn, $_POST['idAtlet']);
				if($result){
					$response = array("status" => 1, "massage" => "Success!", "data" => $result);
				}else{
					$response = array("status" => 0, "massage" => "Some error!", "data" => array());
				}
				break;
			case 'rekamMedis': //get
				isTheseParametersAvailable(array('idAtlet'));
				$statusRead = 2;
				$result=rekamMedis($conn, $_POST['idAtlet'], $statusRead);
				if($result){
					$response = array("status" => 1, "massage" => "Success!", "data" => $result);
				}else{
					$response = array("status" => 0, "massage" => "Some error!", "data" => array());
				}
				break;
			case 'intensitas': //update
				isTheseParametersAvailable(array('idAtlet', 'intensitas'));
				$result=intensitas($conn, $_POST['idAtlet'], $_POST['intensitas']);
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
			case 'statistikCabor': //get
				isTheseParametersAvailable(array('idPsikolog'));
				$result=statistikCabor($conn, $_POST['idPsikolog']);
				if($result){
					$response = array("status" => 1, "massage" => "Success!", "data" => $result);
				}else{
					$response = array("status" => 0, "massage" => "Some error!", "data" => array());
				}
				break;
			case 'profile': //update
				isTheseParametersAvailable(array('idPsikolog', 'userName', 'password', 'passKonfirm', 'alamat', 'telp'));
				$result=profile($conn, $_POST['idPsikolog'], $_POST['userName'], $_POST['password'], $_POST['passKonfirm'], $_POST['alamat'], $_POST['telp']);
				if($result){
					$response = array("status" => 1, "massage" => "Success!", "data" => $result);
				}else{
					$response = array("status" => 0, "massage" => "Some error!", "data" => array());
				}
				break;
			case 'photoProfile': //update
				isTheseParametersAvailable(array('idPsikolog', 'photoProfile'));
				$result=photoProfile($conn, $_POST['idPsikolog'], $_POST['photoProfile']);
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