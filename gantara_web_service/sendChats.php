<?php
	include 'config.php';
	include 'push_notification.php';
	 date_default_timezone_set("Asia/Jakarta");
	if($_SERVER['REQUEST_METHOD'] == "POST"){
		// Get data
		$pengirim = isset($_POST['pengirim']) ? mysqli_real_escape_string($conn, $_POST['pengirim']) : "";
		$penerima = isset($_POST['penerima']) ? mysqli_real_escape_string($conn, $_POST['penerima']) : "";
		$waktu   = date("Y-m-d H:i:s");
		$message = isset($_POST['message']) ? mysqli_real_escape_string($conn, $_POST['message']) : "";
		$jenis_penerima = isset($_POST['jenis_penerima']) ? mysqli_real_escape_string($conn, $_POST['jenis_penerima']):"";

		if($jenis_penerima == 0){
			$sql = "SELECT token FROM atlet WHERE id_atlet ='".$penerima."'";
		}else if($jenis_penerima == 1){
			$sql = "SELECT token FROM psikolog WHERE id_psikolog = '".$penerima."'";
		}

		$qur = mysqli_query($conn, $sql);
		if(mysqli_num_rows($qur) > 0){
			$row = mysqli_fetch_array($qur,MYSQLI_ASSOC);
			$data = array("id_penerima" => $penerima, "id_pengirim" => $pengirim, "waktu" => $waktu, "message" => $message);
			$result = json_decode(send_notification($row['token'],$data));

			// echo result;
			if($result->success == 1)
				$json = array("status" => 1,"msg" => "Success sending message", "data" => $data );
			else $json = array("status" => 0,"msg" => "Failed sending message", "data" => array() );
		}else{
		 	$json = array("status" => 0, "msg" => "No user with current token found!", "data" => array());
		}
	}else{
	 	$json = array("status" => 0, "msg" => "Request method not accepted", "data" => array());
	}
	
	//@mysql_close($conn);
	
	/* Output header */
	header('Content-type: application/json');
	echo json_encode($json);
