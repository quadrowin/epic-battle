<?php

$files = glob('*.png');
sort($files);
$current_column = null;
$current_pattern = null;
$current_image = null;
$row = -1;
$dst_left = 1;
$padding = 2;
$dst_height = 100;

function finish_image($image, $name) {
    if (!$image) {
        return;
    }
    imagepng($image, strtolower($name) . '.png');
}

foreach ($files as $file) {
    preg_match('#^([^_]*)_.*$#', $file, $m);
    if ($m[1] !== $current_pattern) {
        finish_image($current_image, $current_pattern);
        $current_column = 0;
        $current_pattern = $m[1];
        $current_image = imagecreatetruecolor(1024, 128); 
        
        $trans_color = imagecolorallocatealpha($current_image, 0, 0, 0, 127);
        imagecolortransparent($current_image, $trans_color);
        imagealphablending($current_image, false);
        imagesavealpha($current_image, true);
        imagefill($current_image, 1, 1, $trans_color);
        
        $dst_left = 1;
        $row++;
    } else {
        $dst_left += $dst_width + $padding;
    }
    $src_image = imagecreatefrompng($file);
    $src_width = imagesx($src_image);
    $src_height = imagesy($src_image);
    $dst_width = $src_width * $dst_height / $src_height;
    imagecopyresampled(
        $current_image,
        $src_image,
        $dst_left, 1,
        0, 0,
        $dst_width, $dst_height,
        $src_width, $src_height
    );
    
    if ($dst_left === 1) {
        echo "{$current_pattern} {$dst_width}\n";
    }
}

finish_image($current_image, $current_pattern);