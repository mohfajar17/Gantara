<?php

	date_default_timezone_set("Asia/Jakarta");
	
	function signup($conn, $nama, $gander, $tempatLahir, $tanggalLahir, $alamat, $noTelfon, $userName, $pass, $photo){ //add
		$status_verifikasi = 0;

		$input = base64_decode($photo);
    	file_put_contents('Photo/'.$userName.'.png', $input);

		$query = "INSERT INTO psikolog (nama, jenis_kelamin, tempat_lahir, tanggal_lahir, alamat, no_telefon, user_name, password, photo_profile, status_verifikasi) VALUES ('$nama', '$gander', '$tempatLahir', '$tanggalLahir', '$alamat', '$noTelfon', '$userName', '$pass', '".$userName.".png', '$status_verifikasi')";
	    $hasil = mysqli_query($conn,$query);

		mysqli_close($conn);
		return $hasil;
	}

	function login($conn, $userName, $pass, $token){
		$sql = "SELECT * FROM psikolog WHERE user_name = '".$userName."' and password = '".$pass."'";
		$qur = mysqli_query($conn, $sql);

		$row = mysqli_fetch_array($qur,MYSQLI_ASSOC);
		$count = mysqli_num_rows($qur);
		
		if($count>0){
		 	$sql = "UPDATE `psikolog` SET `token` = '".$token."' WHERE `user_name` = '".$userName."'";
			$qur = mysqli_query($conn, $sql);
			$row['token'] = $token;

			$sqlGetAtlet = "SELECT * FROM atlet WHERE id_psikolog = '".$row['id_psikolog']."' and status_verifikasi = 0";
			$qurGetAtlet = mysqli_query($conn, $sqlGetAtlet);
			$rowsAtlet = array();

			if(mysqli_num_rows($qurGetAtlet) > 0){
				while($r = mysqli_fetch_assoc($qurGetAtlet)) {
			    	$rowsAtlet[] = $r;
				}
			}
			$row['atlet'] = $rowsAtlet;
		}else{
		 	$row=array();
		}
		mysqli_close($conn);
		return $row;
	}

	function info($conn){
        $sql = "SELECT * FROM info ORDER BY id DESC";
        $qur = mysqli_query($conn, $sql);

        $count = mysqli_num_rows($qur);
        $row = array();

        if($count>0){
            while($r = mysqli_fetch_assoc($qur)) {
                $row[] = $r;
            }
        }else{
            $row = array();
        }
		mysqli_close($conn);
	    return $row;
	}

	function addInfo($conn, $idPsikolog, $judul, $dari, $untuk, $isiInfo){ //add
		$waktu = date("Y-m-d H:i:s");

		$query = "INSERT INTO info (id_psikolog, judul,dari,untuk,waktu,isi_info) values ('$idPsikolog','$judul','$dari','$untuk','$waktu','$isiInfo')";
	    $hasil = mysqli_query($conn,$query);
	
		mysqli_close($conn);
	    return $hasil;
	}

	function form($conn, $idPsikolog){
		$status = 1;
		$sql = "SELECT form.*, atlet.nama, atlet.cabang_olahraga, atlet.user_name FROM form INNER JOIN atlet ON atlet.id_atlet = form.id_atlet WHERE form.id_psikolog = '".$idPsikolog."' and form.status = '".$status."'";
		$qur = mysqli_query($conn, $sql);

		$row = mysqli_fetch_array($qur,MYSQLI_ASSOC);
		$count = mysqli_num_rows($qur);
		
		if($count>0){
			$sqlGetForm = "SELECT form.*, atlet.nama, atlet.cabang_olahraga, atlet.user_name FROM form INNER JOIN atlet ON atlet.id_atlet = form.id_atlet WHERE form.id_psikolog = '".$row['id_psikolog']."' and form.status = '".$row['status']."' ORDER BY form.waktu_input DESC";
			$qurGetForm = mysqli_query($conn, $sqlGetForm);
			$rowsForm = array();

			if(mysqli_num_rows($qurGetForm) > 0){
				while($r = mysqli_fetch_assoc($qurGetForm)) {
			    	$rowsForm[] = $r;
				}
			}
		}else{
		 	$rowsForm = array();
		}
		mysqli_close($conn);
		return $rowsForm;
	}

	function addTanggapan($conn, $idForm, $catatanPsikolog){ //update
		$waktu = date("Y-m-d H:i:s");
		$status = 1;

		$query = "UPDATE `form` SET `waktu_rm` = '".$waktu."', `isi_rm` = '".$catatanPsikolog."', `status_rm` = '".$status."' WHERE `id_form` = '".$idForm."'";
    	$hasil = mysqli_query($conn,$query);

		mysqli_close($conn);
    	return $hasil;
	}

	function readForm($conn, $idForm){
		$status = 0;
		
		$query = "UPDATE `form` SET `status` = '".$status."' WHERE `id_form` = '".$idForm."'";
    	$hasil = mysqli_query($conn,$query);

		mysqli_close($conn);
    	return $hasil;
	}

	function daftarAtlet($conn, $idPsikolog){ //get
		$status = 0;
		$sql = "SELECT * FROM atlet WHERE id_psikolog = '".$idPsikolog."'";
		$qur = mysqli_query($conn, $sql);

		$row = mysqli_fetch_array($qur,MYSQLI_ASSOC);
		$count = mysqli_num_rows($qur);
		
		if($count>0){
			$sqlGetForm = "SELECT * FROM atlet WHERE id_psikolog = '".$row['id_psikolog']."' and status_verifikasi = '".$status."' ORDER BY nama ASC";
			$qurGetForm = mysqli_query($conn, $sqlGetForm);
			$rowsAtlet = array();

			if(mysqli_num_rows($qurGetForm) > 0){
				while($r = mysqli_fetch_assoc($qurGetForm)) {
			    	$rowsAtlet[] = $r;
				}
			}
		}else{
		 	$rowsAtlet = array();
		}
		mysqli_close($conn);
		return $rowsAtlet;
	}

	function addAtlet($conn, $idPsikolog){ //get
		$status = 1;
		$sql = "SELECT * FROM atlet WHERE id_psikolog = '".$idPsikolog."'";
		$qur = mysqli_query($conn, $sql);

		$row = mysqli_fetch_array($qur,MYSQLI_ASSOC);
		$count = mysqli_num_rows($qur);
		
		if($count>0){
			$sqlGetForm = "SELECT * FROM atlet WHERE id_psikolog = '".$row['id_psikolog']."' and status_verifikasi = '".$status."' ORDER BY nama ASC";
			$qurGetForm = mysqli_query($conn, $sqlGetForm);
			$rowsAtlet = array();

			if(mysqli_num_rows($qurGetForm) > 0){
				while($r = mysqli_fetch_assoc($qurGetForm)) {
			    	$rowsAtlet[] = $r;
				}
			}
		}else{
		 	$rowsAtlet = array();
		}
		mysqli_close($conn);
		return $rowsAtlet;
	}

	function checkAddAtlet($conn, $idAtlet){ //get
		$sql = "SELECT * FROM atlet WHERE id_atlet = '".$id_atlet."'";
		$qur = mysqli_query($conn, $sql);

		$row = mysqli_fetch_array($qur,MYSQLI_ASSOC);
		$count = mysqli_num_rows($qur);

		mysqli_close($conn);
		return $count;
	}

	function rekamMedis($conn, $idAtlet, $statusRead){ //get
		$sql = "SELECT id_form, id_atlet, id_psikolog, waktu_input, sesi_latihan, catatan_fisik, kendala_mental_skill, hal_positif, isi_rm, status_rm FROM form WHERE id_atlet = '".$idAtlet."' AND status_rm = '".$statusRead."'";

		$qur = mysqli_query($conn, $sql);

		$row = mysqli_fetch_array($qur,MYSQLI_ASSOC);
		$count = mysqli_num_rows($qur);
		
		if($count>0){
			$sqlGetForm = "SELECT id_form, id_atlet, id_psikolog, waktu_input, sesi_latihan, catatan_fisik, kendala_mental_skill, hal_positif, isi_rm FROM form WHERE id_atlet = '".$row['id_atlet']."' AND status_rm = '".$row['status_rm']."' ORDER BY id_form DESC";
			$qurGetForm = mysqli_query($conn, $sqlGetForm);
			$rowsForm = array();

			if(mysqli_num_rows($qurGetForm) > 0){
				while($r = mysqli_fetch_assoc($qurGetForm)) {
			    	$rowsForm[] = $r;
				}
			}
			$row = $rowsForm;
		}else{
		 	$row = array();
		}

		mysqli_close($conn);
		return $row;
	}

	function intensitas($conn, $idAtlet, $intensitas){ //update
		$sql = "SELECT * FROM atlet WHERE id_atlet = '".$idAtlet."'";
		$qur = mysqli_query($conn, $sql);

		$row = mysqli_fetch_array($qur,MYSQLI_ASSOC);
		$count = mysqli_num_rows($qur);
		
		if($count>0){
		 	$sql = "UPDATE `atlet` SET `intensitas_target` = '".$intensitas."' WHERE `id_atlet` = '".$idAtlet."'";
			$qur = mysqli_query($conn, $sql);
			$row['intensitas_target'] = $intensitas;
		}else{
		 	$row = array();
		}

		mysqli_close($conn);
		return $row;
	}

	function statistik($conn, $idAtlet){ //get
		$sql = "SELECT * FROM form WHERE id_atlet = '".$idAtlet."' and waktu_input > DATE_SUB(NOW(), INTERVAL 1 MONTH)";
		$qur = mysqli_query($conn, $sql);

		$row = mysqli_fetch_array($qur,MYSQLI_ASSOC);
		$count = mysqli_num_rows($qur);
		
		if($count>0){
			$sqlGetForm = "SELECT * FROM form WHERE id_atlet = '".$row['id_atlet']."' and waktu_input > DATE_SUB(NOW(), INTERVAL 1 MONTH) ORDER BY id_form ASC";
			$qurGetForm = mysqli_query($conn, $sqlGetForm);
			$rowsForm = array();

			if(mysqli_num_rows($qurGetForm) > 0){
				while($r = mysqli_fetch_assoc($qurGetForm)) {
			    	$rowsForm[] = $r;
				}
			}
		 	$row = $rowsForm;
		}else{
		 	$row = array();
		}

		mysqli_close($conn);
		return $row;
	}

	function statistikCabor($conn, $idPsikolog){ //get
		$sql = "SELECT id_form,id_atlet,id_psikolog,nama,cabang_olahraga,waktu_input,scoring_mental,scoring_fisik,intensitas FROM form WHERE id_psikolog = '".$idPsikolog."' AND MONTH(waktu_input) = MONTH(CURDATE()) AND YEAR(waktu_input) = YEAR(CURDATE()) ORDER BY id_form ASC";
		$qur = mysqli_query($conn, $sql);

		$row = mysqli_fetch_array($qur,MYSQLI_ASSOC);
		$count = mysqli_num_rows($qur);
		if($count>0){
			$rowsForm = array();

			$sqlGetMental = "SELECT id_form,id_atlet,id_psikolog,nama,cabang_olahraga,waktu_input,scoring_mental FROM form WHERE id_psikolog = '".$row['id_psikolog']."' AND MONTH(waktu_input) = MONTH(CURDATE()) AND YEAR(waktu_input) = YEAR(CURDATE()) ORDER BY id_form ASC";
			$qurGetMental = mysqli_query($conn, $sqlGetMental);
			$rowsMental = array();
			if(mysqli_num_rows($qurGetMental) > 0){
				while($mental = mysqli_fetch_assoc($qurGetMental)) {
			    	$rowsMental[] = $mental;
				}
			}

			$sqlGetFisik = "SELECT id_form,id_atlet,id_psikolog,nama,cabang_olahraga,waktu_input,scoring_fisik FROM form WHERE id_psikolog = '".$row['id_psikolog']."' AND MONTH(waktu_input) = MONTH(CURDATE()) AND YEAR(waktu_input) = YEAR(CURDATE()) ORDER BY id_form ASC";
			$qurGetFisik = mysqli_query($conn, $sqlGetFisik);
			$rowsFisik = array();
			if(mysqli_num_rows($qurGetFisik) > 0){
				while($fisik = mysqli_fetch_assoc($qurGetFisik)) {
			    	$rowsFisik[] = $fisik;
				}
			}

			$sqlGetIntensitas = "SELECT id_form,id_atlet,id_psikolog,nama,cabang_olahraga,waktu_input,intensitas FROM form WHERE id_psikolog = '".$row['id_psikolog']."' AND MONTH(waktu_input) = MONTH(CURDATE()) AND YEAR(waktu_input) = YEAR(CURDATE()) ORDER BY id_form ASC";
			$qurGetIntensitas = mysqli_query($conn, $sqlGetIntensitas);
			$rowsIntensitas = array();
			if(mysqli_num_rows($qurGetIntensitas) > 0){
				while($intensitas = mysqli_fetch_assoc($qurGetIntensitas)) {
			    	$rowsIntensitas[] = $intensitas;
				}
			}

			$rowsForm = array("fisik" => $rowsFisik, "mental" => $rowsMental, "intensitas" => $rowsIntensitas);
		}else{
		 	$rowsForm = array();
		}

		mysqli_close($conn);
		return $rowsForm;
	}

	function profile($conn, $idPsikolog, $userName, $password, $passKonfirm, $alamat, $telp){ //update
		$sql = "SELECT * FROM psikolog WHERE id_psikolog = '".$idPsikolog."' and password = '".$passKonfirm."'";
		$qur = mysqli_query($conn, $sql);

		$row = mysqli_fetch_array($qur,MYSQLI_ASSOC);
		$count = mysqli_num_rows($qur);

		if($count>0){
			$sql = "UPDATE `psikolog` SET `alamat`='".$alamat."', `no_telefon`='".$telp."', `user_name`='".$userName."', `password`='".$password."' WHERE id_psikolog = '".$idPsikolog."'";
			$qur = mysqli_query($conn, $sql);
			$row['alamat'] = $alamat;
			$row['telp'] = $telp;
			$row['user_name'] = $userName;
			$row['password'] = $password;
		}else{
		 	$row = array();
		}

		mysqli_close($conn);
		return $row;
	}

	function photoProfile($conn, $idPsikolog, $photoProfile){ //update
		$input = base64_decode($photoProfile);
    	file_put_contents('photo/'.$idPsikolog.'.png', $input);

		// Insert data into data base
		$sql = "SELECT * FROM psikolog WHERE id_psikolog = '".$idPsikolog."'";
		$qur = mysqli_query($conn, $sql);

		$row = mysqli_fetch_array($qur,MYSQLI_ASSOC);
		$count = mysqli_num_rows($qur);

		if($count>0){
			$sql = "UPDATE `psikolog` SET `photo_profile` = '".$idPsikolog.".png' WHERE id_psikolog = '".$idPsikolog."'";
			$qur = mysqli_query($conn, $sql);
			$row['id_psikolog'] = $idPsikolog;
			$row['photo_profile'] = $photoProfile;
		}else{
		 	$row = array();
		}

		mysqli_close($conn);
		return $row;
	}
?>