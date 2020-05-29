<?php

	function signup($conn, $idPsikolog, $nama, $cabor, $gander, $tempatLahir, $tanggalLahir, $alamat, $kotaAsal, $noTelfon, $userName, $pass, $photo){ //add
		$status_verifikasi = 0;
		$intensitas_target = 60;

		$input = base64_decode($photo);
    	file_put_contents('Photo/'.$userName.'.png', $input);

		$query = "INSERT INTO atlet (id_psikolog, nama, cabang_olahraga, jenis_kelamin, tempat_lahir, tanggal_lahir, alamat, kota_asal, no_telefon, user_name, password, photo_profile, intensitas_target, status_verifikasi) VALUES ('$idPsikolog', '$nama', '$cabor', '$gander', '$tempatLahir', '$tanggalLahir', '$alamat', '$kotaAsal', '$noTelfon', '$userName', '$pass', '".$userName.".png', '$intensitas_target', '$status_verifikasi')";
	    $hasil = mysqli_query($conn,$query);

		mysqli_close($conn);
		return $hasil;
	}

	function login($conn, $userName, $pass, $token){ //get
		$sql = "SELECT * FROM atlet WHERE user_name = '".$userName."' and password = '".$pass."'";
		$qur = mysqli_query($conn, $sql);

		$row = mysqli_fetch_array($qur,MYSQLI_ASSOC);
		$count = mysqli_num_rows($qur);
		
		if($count>0){
		 	$sql = "UPDATE `atlet` SET `token` = '".$token."' WHERE `user_name` = '".$userName."'";
			$qur = mysqli_query($conn, $sql);
			$row['token'] = $token;
		}else{
		 	$row=array();
		}

		mysqli_close($conn);
		return $row;
	}

	function info($conn){ //get
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

	function addForm($conn, $idAtlet, $idPsikolog, $sesiLatihan, $antusiasmePreLatihan, $antusiasmePosLatihan, $fisik, $catatanFisik, $stres, $konsentrasi, $keyakinan, $target, $lelah, $komunikasi, $intensitas, $hidrasi, $tidur, $nutrisi, $recovery, $recoveryLain, $mentalSkill, $mentalSkillLain, $kendalaMentalSkill, $saranMasalah, $saranMasalahLain, $halPositif, $scoringMental, $scoringFisik, $intensitasTarget, $status){
		$waktu = date("Y-m-d");

		$query = "INSERT INTO form (id_atlet, id_psikolog, waktu_input, sesi_latihan, antusiasme_pre_latih, antusiasme_pos_latih, fisik, catatan_fisik, stres, konsentrasi, keyakinan, target, lelah, komunikasi, intensitas, hidrasi, tidur, nutrisi, recovery, recovery_lain, mental_skill, mental_skill_lain, kendala_mental_skill, saran_masalah, saran_masalah_lain, hal_positif, scoring_mental, scoring_fisik, intensitas_target, status) values ('$idAtlet', '$idPsikolog', '$waktu', '$sesiLatihan', '$antusiasmePreLatihan', '$antusiasmePosLatihan', '$fisik', '$catatanFisik', '$stres', '$konsentrasi', '$keyakinan', '$target', '$lelah', '$komunikasi', '$intensitas', '$hidrasi', '$tidur', '$nutrisi', '$recovery', '$recoveryLain', '$mentalSkill', '$mentalSkillLain', '$kendalaMentalSkill', '$saranMasalah', '$saranMasalahLain', '$halPositif', '$scoringMental', '$scoringFisik', '$intensitasTarget', '$status')";

	    $hasil = mysqli_query($conn,$query);

		mysqli_close($conn);
	    return $hasil;
	}

	function tanggapan($conn, $idAtlet, $statusRead){ //list tanggapan/rekam medis => beda statusRead
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

	function readStatusRekamMedis($conn, $idForm, $statusRead){ //update status dari tanggaan ke rekam medis
		$query = "UPDATE `form` SET `status_rm` = '".$statusRead."' WHERE `id_form` = '".$idForm."'";
    	$hasil = mysqli_query($conn,$query);

		mysqli_close($conn);
	    return $hasil;
	}

	function statistik($conn, $idAtlet){ //get
		$sql = "SELECT * FROM form WHERE id_atlet = '".$idAtlet."' and waktu_input > DATE_SUB(NOW(), INTERVAL 1 WEEK)";
		$qur = mysqli_query($conn, $sql);

		$row = mysqli_fetch_array($qur,MYSQLI_ASSOC);
		$count = mysqli_num_rows($qur);
		
		if($count>0){
			$sqlGetForm = "SELECT * FROM form WHERE id_atlet = '".$row['id_atlet']."' and waktu_input > DATE_SUB(NOW(), INTERVAL 1 WEEK) ORDER BY id_form ASC";
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

	function profile($conn, $idAtlet, $idPsikolog, $userName, $pass, $passKonf, $alamat, $kotaAsal, $noTelfon, $cabor){
		$sql = "SELECT * FROM atlet WHERE id_atlet = '".$idAtlet."' and password = '".$passKonf."'";
		$qur = mysqli_query($conn, $sql);

		$row = mysqli_fetch_array($qur,MYSQLI_ASSOC);
		$count = mysqli_num_rows($qur);

		if($count>0){
			$sql = "UPDATE `atlet` SET `id_psikolog`='".$idPsikolog."', `cabang_olahraga`='".$cabor."', `alamat`='".$alamat."', `kota_asal`='".$kotaAsal."', `no_telefon`='".$noTelfon."', `user_name`='".$userName."', `password`='".$pass."' WHERE id_atlet = '".$idAtlet."'";
			$qur = mysqli_query($conn, $sql);
			$row['id_psikolog'] = $idPsikolog;
			$row['cabang_olahraga'] = $cabor;
			$row['alamat'] = $alamat;
			$row['kota_asal'] = $kotaAsal;
			$row['no_telefon'] = $noTelfon;
			$row['user_name'] = $userName;
			$row['password'] = $pass;
		}else{
		 	$row = array();
		}
		
		mysqli_close($conn);
		return $row;
	}

	function photoProfile($conn, $userName, $photoProfile){
		$input = base64_decode($photoProfile);
    	file_put_contents('photo/'.$userName.'.png', $input);

		// Insert data into data base
		$sql = "SELECT * FROM atlet WHERE id_atlet = '".$userName."'";
		$qur = mysqli_query($conn, $sql);

		$row = mysqli_fetch_array($qur,MYSQLI_ASSOC);
		$count = mysqli_num_rows($qur);

		if($count>0){
			$sql = "UPDATE `atlet` SET `photo_profile` = '".$userName.".png' WHERE user_name = '".$userName."'";
			$qur = mysqli_query($conn, $sql);
			$row['user_name'] = $userName;
			$row['photo_profile'] = $photoProfile;
		}else{
		 	$row = array();
		}

		mysqli_close($conn);
		return $row;
	}

	function sharePref($conn, $userName){
		$sql = "SELECT * FROM atlet WHERE user_name = '".$userName."'";
		$qur = mysqli_query($conn, $sql);

		$row = mysqli_fetch_array($qur,MYSQLI_ASSOC);

		mysqli_close($conn);
		return $row;
	}
?>