#! /usr/bin/python3
# noinspection PyBroadException
import bpy, random

"""
This script can not be run as standalone.
To use it install Blender 4.3.2 or later
  and use the Scripting tab to run it.
Further development can be done by installing 
  the blender python extension in VSCode. 
"""

# List of names for the rendered frames
frame_names = [
    "Coin_1", 
    "Coin_5", 
    "Coin_10",
    "Coin_25",
    "Coin_50",

    'Paper_1',
    'Paper_2',
    'Paper_5',
    'Paper_10',
    'Paper_25',
    'Paper_50',
    'Paper_100',
    'Paper_250',
    'Paper_500',
    'Paper_1000',
    ] # Replace with your actual list of names


def rng():
    bpy.data.node_groups["Geometry Nodes"].nodes["Wave Texture"].inputs[6].default_value = random.random()*10
    bpy.data.node_groups["Geometry Nodes"].nodes["Noise Texture"].inputs[1].default_value = random.random()*10



def run(scale, type):

    # Get scene and render settings
    scene = bpy.context.scene
    render = scene.render

    # Store original output settings
    original_path = render.filepath


    bpy.data.scenes["Scene"].render.resolution_x = scale          # Render -> Format -> Resolution X
    bpy.data.scenes["Scene"].render.resolution_y = scale          # Render -> Format -> Resolution Y


    # Build Coins
    for frame in range(0, 4+1):
        # Set current frame
        scene.frame_set(frame)
        
        # Set output path for this frame
        frame_name = frame_names[frame]
        render.filepath = f"//images/{type}/{frame_name}.png"
        
        # Render frame
        rng()
        bpy.context.scene.camera = bpy.data.objects["CoinCam"]
        bpy.ops.render.render(write_still=True)


    # Build Paper Bills
    for frame in range(5, 5+9+1):
        scene.frame_set(frame)

            # Render Bill Texture
        bpy.data.scenes["Scene"].render.resolution_x = scale*5
        bpy.data.scenes["Scene"].render.resolution_y = scale*2

        # Set output path for this frame
        frame_name = frame_names[frame]
        render.filepath = f"//../build/images/{type}/{frame_name}.png"

        # Render frame
        bpy.context.scene.camera = bpy.data.objects["BillBuildCam"]
        bpy.ops.render.render(write_still=True)


        # Update texture image with the rendered bill texture
        paper_bill_texture = bpy.data.materials["PaperBill"].node_tree.nodes["Image Texture"]
        paper_bill_texture.image = bpy.data.images.load(render.filepath)


            # Render Bill
        bpy.data.scenes["Scene"].render.resolution_x = scale*2
        bpy.data.scenes["Scene"].render.resolution_y = scale
        
        # Set output path for this frame
        frame_name = frame_names[frame]
        render.filepath = f"//images/{type}/{frame_name}.png"

        # Render frame
        rng()
        bpy.context.scene.camera = bpy.data.objects["BillCam"]
        bpy.ops.render.render(write_still=True)

    # Restore original render path
    render.filepath = original_path


for each in [
    [64, "icon"],
    [512, "image"],
]:
    run(each[0], each[1])


# print a BEL, your terminal can detect
# it and send a notification if closed
print('\a')
