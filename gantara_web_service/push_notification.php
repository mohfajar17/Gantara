<?php 
	function send_notification ($tokens, $message)
	{
		$url = 'https://fcm.googleapis.com/fcm/send';
		$fields = array(
			 'to' => $tokens,
			 'data' => array("message" =>$message)
			);
		$headers = array(
			'Authorization:key = AAAAu1LlneM:APA91bEjkMxXVteXzOZvCMJB9YsSHltVoQAxInED38Ll3KXfwTDQQDVeHuYa7PHZ8AbQdVLkOezF44oWmCckDyjbmXCrMHKNkkI8xA2P-pDk1CfDlYjhd3pE_aevh-JFIspRPJdvaXdD',
			'Content-Type: application/json'
			);

	    $ch = curl_init();
        curl_setopt( $ch, CURLOPT_IPRESOLVE, CURL_IPRESOLVE_V4);
           //curl_setopt( $ch, CURLOPT_IPRESOLVE, CURL_IPRESOLVE_V4);
        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt ($ch, CURLOPT_SSL_VERIFYHOST, 0);  
        curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
        curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
        $result = curl_exec($ch);           
        if ($result === FALSE) {
            die('Curl failed: ' . curl_error($ch));
        }
        curl_close($ch);
        return $result;
	}
	
	if(isset($_POST['token'])){
		// include 'dbconfig.php';
		$tokens = $_POST['token'];
		$message = $_POST['message'];
		$message_status = send_notification($tokens, $message);
		echo $message_status;

	}

	//$tokens[0] = "cSNHrGfHrcM:APA91bF6";
	//$tokens[1] = "fmZ1yl0PP2A:APA91bEw";
 ?>
 