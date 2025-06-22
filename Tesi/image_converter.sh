#!/bin/bash

# Change into the "tif" directory to find the input files
cd immagini_old || { echo ERROR: Subdirectory tif not found; exit 1; }

# Loop through all files ending in ".tif"
for f in *.pdf; do

   # Determine output filename
   out=${f%%pdf}
   out="../pdf/${out}pdf"

   # Show the command we would run
   gs -dPDFA -dBATCH -dNOPAUSE -sProcessColorModel=DeviceRGB -sColorConversionStrategy=UseDeviceIndependentColor -sDEVICE=pdfwrite -dPDFACompatibilityPolicy=1 -sOutputFile="$out" "$f"
done