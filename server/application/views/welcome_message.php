<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Welcome to CodeIgniter</title>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>/asserts/css/mycss.css" />
</head>
<body>

<div id="container">
	<h1 >Server</h1>
	<h1 id="message">Status: </h1>
	<div id="body">

		<div class="grid-container">

				<?php for($i=0;$i<8;$i++){?>
						<div class="grid-item">
							<p>💻&nbsp&nbsp Pc : <?php echo $i+1;?></p>
							<div class="two_p">
								<p>Status:</p>
								<p id="status_pc<?php echo $i;?>">Offline</p>
							</div>
							<div visibility="hidden" id='Online-Offline<?= $i; ?>'>
								<div id='Online-Offlinebtn<?= $i; ?>'>
													<div class="two_p">
														<p>Price:&nbsp&nbsp</p>
														<p id="price_pc<?php echo $i;?>"s>€&nbsp0.00</p>
													</div>
													<div class="two_p">
														<p>Time Running: </p>
														<p id="timerunning_pc<?php echo $i;?>">00:00</p>
													</div>
									</div>
									<button id="lock_pc<?php echo $i;?>" onclick="setExcute(<?= $i+1?>,lock)">Lock</button>&nbsp
									<button id="unlock_pc<?php echo $i;?>" onclick="setExcute(<?= $i+1?>,unlock)">unLock</button>&nbsp
									<button id="shutdown<?php echo $i;?>" onclick="setExcute(<?= $i+1?>,shutdownStatus)" >ShutDown</button>
							</div>
						</div>
				<?php } ?>
		</div>
		</div>
</div>
  <script type="text/javascript"  src="<?php echo base_url(); ?>/asserts/js/declare.js" ></script>
	<script type="text/javascript"  src="<?php echo base_url(); ?>/asserts/js/script.js" ></script>
</body>
</html>
